package top.hdsw.shardingjdbcwriteread;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.hdsw.shardingjdbcwriteread.entity.Products;
import top.hdsw.shardingjdbcwriteread.mapper.ProductsMapper;

import java.util.List;

@SpringBootTest
class ShardingJdbcWriteReadApplicationTests {


	@Autowired
	private ProductsMapper productsMapper;

	@Test
	public void testInsert(){

		Products products = new Products();
		products.setPname("电视机");
		products.setPrice(2000);
		products.setFlag("1");

		productsMapper.insert(products);
	}


	//事务测试
	@Transactional
	@Test
	public void testTrans(){

		Products products = new Products();
		products.setPname("洗碗机");
		products.setPrice(1000);
		products.setFlag("1");
		productsMapper.insert(products);

		QueryWrapper<Products> wrapper = new QueryWrapper<>();
		wrapper.eq("pname","洗碗机");
		List<Products> list = productsMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

	@Test
	public void testQuery(){
		QueryWrapper<Products> wrapper = new QueryWrapper<>();
		wrapper.eq("pname","洗碗机");
		List<Products> list = productsMapper.selectList(wrapper);
		list.forEach(System.out::println);
	}

}
