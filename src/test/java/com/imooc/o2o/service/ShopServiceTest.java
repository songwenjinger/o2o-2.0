package com.imooc.o2o.service;


import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.*;
import com.imooc.o2o.enums.ShopStateEnum;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTest  {
    @Autowired
    private ShopService shopService;
    @Autowired
	private HeadLineService headLineService;
    @Autowired
	private ShopCategoryService shopCategoryService;
    @Test
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
		Area area = new Area();
		ShopCategory sc = new ShopCategory();
		owner.setUserId(8L);
		area.setAreaId(3);
		sc.setShopCategoryId(10L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(sc);
		shop.setShopName("测试的店铺2");
		shop.setShopDesc("test2");
		shop.setShopAddr("test2");
		shop.setPhone("13810524526");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		String filePath="F:\\def.jpg";
		ShopExecution se = shopService.addShop(shop, path2MultipartFile(filePath));
		System.out.println("ShopExecution.state" + se.getState());
		System.out.println("ShopExecution.stateInfo" + se.getStateInfo());

        System.out.println(se.getState());
	}
	/**
	 * filePath to MultipartFile
	 *
	 * @param filePath
		 */
	private MultipartFile path2MultipartFile(String filePath) throws IOException {
		File file = new File(filePath);
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
				IOUtils.toByteArray(input));
		return multipartFile;
	}

	/**
	 * 测试水印[缩略图的创建]
	 * @throws IOException
	 */
	@Test
	@Ignore
	public void test() throws IOException {
		 String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		basePath= URLDecoder.decode(basePath,"utf-8");
		System.out.println(basePath);
		Thumbnails.of(new File("F://def.jpg"))
				.size(200,200).watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File(basePath + "watermark.jpg")),0.25f).outputQuality(0.8f)
		.toFile("F://watermark1.jpg");
	}

	/**
	 * 测试改变店铺的信息
	 * @throws IOException
	 */
	@Test
	public void testModifyShop() throws IOException {
		Shop shop=new Shop();
		shop.setShopId(48L);
		shop.setShopName("看风景的店铺");
		String filePath="F:\\def.jpg";
		ShopExecution shopExecution=shopService.modifyShop(shop, path2MultipartFile(filePath));
		System.out.println(shopExecution.getShop().getShopImg());
	}
	/**
	 * 测试查询店铺列表
 	 */

	@Test
	public void testGetShopList()
	{
		Shop shopCondition=new Shop();
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(10L);
		shopCondition.setShopCategory(sc);
		ShopExecution se=shopService.getShopList(shopCondition,3,2);
		System.out.println("店铺列表数为:"+se.getShopList().size());
		System.out.println("店铺总数为:"+se.getCount());
	}


	@Test
	public void testQueryArea() throws IOException {
		HeadLine headLine=new HeadLine();
		headLine.setEnableStatus(1);
		List<HeadLine> headLineList=headLineService.getHeadLineList(headLine);
		System.out.println(headLineList.size());
	}
	@Test
	public void testQueryShopCategory()
	{
		/**
		 * 传参数为空时，会返回所有的列表
		 */
		List<ShopCategory> shopCategoryList=shopCategoryService.getShopCategoryList(null);
		System.out.println(shopCategoryList.size());
	}
}
