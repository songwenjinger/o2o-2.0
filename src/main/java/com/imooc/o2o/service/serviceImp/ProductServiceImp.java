package com.imooc.o2o.service.serviceImp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exception.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductByProductId(productId);
	}

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, MultipartFile thumbnail,
									   List<MultipartFile> productImgs) throws Exception {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				try {
					addThumbnail(product, thumbnail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品失败:" + e.toString());
			}
			if (productImgs != null && productImgs.size() > 0) {
				addProductImgs(product, productImgs);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
//1.若缩略图参数有值，则处理缩略图
//若原先存在缩略图则先删除在添加新图，之后获取缩略图相对路径并赋值给produxt
//2若商品详情图列表参数优质，对商品详情图列表进行同样的操作
//3将tb_product_img下面的该商品的商品详情图记录全部清除
//4.更新tb_product_img一级tb_product的信息
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, MultipartFile thumbnail,
										  List<MultipartFile> productImgs) throws RuntimeException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			System.out.println("进入product函数");
			product.setLastEditTime(new Date());
			//若商品缩略图不为空原因缩略图不为空则删除原有的缩略图并添加
			if (thumbnail != null) {
				//先获取一遍原有信息，因为原来的信息里面有原图片地址
				Product tempProduct = productDao.queryProductByProductId(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					FileUtil.deleteFile(tempProduct.getImgAddr());
				}
				try {
					addThumbnail(product, thumbnail);
				}  catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (productImgs != null && productImgs.size() > 0) {
				deleteProductImgs(product.getProductId());
				try {
					addProductImgs(product, productImgs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				System.out.println("update语句要开始执行了");
				int effectedNum = productDao.updateProduct(product);
				System.out.println("影响的行数"+effectedNum);
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new RuntimeException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void addProductImgs(Product product, List<MultipartFile> productImgs) throws Exception {

		if (productImgs != null && productImgs.size() > 0) {
			List<ProductImg> productImgList = new ArrayList<ProductImg>();
			for (MultipartFile multipartFile : productImgs) {
				ProductImg productImg = new ProductImg();
				File temp=MultiPartFileToFile.multipartFileToFile(multipartFile);
				productImg.setImgAddr(temp.getName());
				productImg.setProductId(product.getProductId());
				productImg.setCreateTime(new Date());
				productImgList.add(productImg);
			}
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	private void deleteProductImgs(long productId) {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			FileUtil.deleteFile(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}

	private void addThumbnail(Product product, MultipartFile thumbnail) throws Exception {
		File temp=MultiPartFileToFile.multipartFileToFile(thumbnail);
		QiNiuYunFileUtils.fileUpload(temp);
		product.setImgAddr(QiNiuYunFileUtils.getFileDownloadUrl(temp.getName()));
	}
}