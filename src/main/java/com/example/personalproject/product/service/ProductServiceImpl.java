package com.example.personalproject.product.service;


import static com.example.personalproject.product.model.OrderStatus.REQ;

import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.member.entity.Member;
import com.example.personalproject.member.repository.MemberRepository;
import com.example.personalproject.product.dto.ProductDto;
import com.example.personalproject.product.entity.Cart;
import com.example.personalproject.product.entity.Product;
import com.example.personalproject.product.mapper.ProductMapper;
import com.example.personalproject.product.model.CartInput;
import com.example.personalproject.product.model.OrderStatus;
import com.example.personalproject.product.model.ProductInput;
import com.example.personalproject.product.model.ProductParam;
import com.example.personalproject.product.repository.CartRepository;
import com.example.personalproject.product.repository.ProductRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final CartRepository cartRepository;
	private final MemberRepository memberRepository;


	private LocalDate getLocalDate(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			return LocalDate.parse(value, formatter);
		} catch (Exception e) {

		}

		return null;
	}

	@Override
	public boolean add(ProductInput parameter) {

		Product product = Product.builder()
			.categoryId(parameter.getCategoryId())
			.productName(parameter.getProductName())
			.keyword(parameter.getKeyword())
			.summary(parameter.getSummary())
			.contents(parameter.getContents())
			.price(parameter.getPrice())
			.productQuantity(parameter.getQuantity())
			.regDt(LocalDateTime.now())
			.filename(parameter.getFilename())
			.urlFilename(parameter.getUrlFilename())
			.build();
		productRepository.save(product);

		return true;
	}

	@Override
	public boolean set(ProductInput parameter) {
		Optional<Product> optionalProduct = productRepository.findById(parameter.getProductId());
		if (optionalProduct.isEmpty()) {
			return false;
		}

		Product product = optionalProduct.get();
		product.setCategoryId(parameter.getCategoryId());
		product.setProductName(parameter.getProductName());
		product.setProductQuantity(parameter.getQuantity());
		product.setKeyword(parameter.getKeyword());
		product.setSummary(parameter.getSummary());
		product.setContents(parameter.getContents());
		product.setPrice(parameter.getPrice());
		product.setUdtDt(LocalDateTime.now());
		product.setFilename(parameter.getFilename());
		product.setUrlFilename(parameter.getUrlFilename());
		productRepository.save(product);

		return true;
	}

	@Override
	public List<ProductDto> list(ProductParam parameter) {

		long totalCount = productMapper.selectListCount(parameter);

		List<ProductDto> list = productMapper.selectList(parameter);
		if (!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for (ProductDto x : list) {
				x.setTotalCount(totalCount);
				x.setSeq(totalCount - parameter.getPageStart() - i);
				i++;
			}
		}

		return list;
	}

	@Override
	public ProductDto getById(long id) {
		return productRepository.findById(id).map(ProductDto::of).orElse(null);
	}

	@Override
	public boolean del(String idList) {

		if (idList != null && idList.length() > 0) {
			String[] ids = idList.split(",");
			for (String x : ids) {
				long id = 0L;
				try {
					id = Long.parseLong(x);
				} catch (Exception e) {
				}

				if (id > 0) {
					productRepository.deleteById(id);
				}
			}
		}

		return true;
	}

	@Override
	public List<ProductDto> frontList(ProductParam parameter) {
		if (parameter.getCategoryId() < 1) {
			List<Product> productList = productRepository.findAll();
			return ProductDto.of(productList);
		}

		Optional<List<Product>> optionalProducts = productRepository.findByCategoryId(
			parameter.getCategoryId());
		if (optionalProducts.isPresent()) {
			return ProductDto.of(optionalProducts.get());
		}
		return null;
	}

	@Override
	public ProductDto frontDetail(long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent()) {
			return ProductDto.of(optionalProduct.get());
		}
		return null;
	}


	@Override
	public List<ProductDto> listAll() {

		List<Product> productList = productRepository.findAll();

		return ProductDto.of(productList);
	}

	@Override
	public ServiceResult req(CartInput parameter) {
		ServiceResult result = new ServiceResult();

		Optional<Product> optionalProduct = productRepository.findById(parameter.getProductId());
		if (optionalProduct.isEmpty()) {
			result.setResult(false);
			result.setMessage("?????? ????????? ???????????? ????????????.");
			return result;
		}
		Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
		if (optionalMember.isEmpty()) {
			result.setResult(false);
			result.setMessage("?????? ????????? ???????????? ????????????.");
			return result;
		}
		Member user = optionalMember.get();
		Product product = optionalProduct.get();

		Cart cart = Cart.builder()
			.product(product)
			.user(user)
			.selectedQuantity(parameter.getSelectedQuantity())
			.payPrice(product.getPrice())
			.regDt(LocalDateTime.now())
			.orderStatus(REQ)
			.build();
		cartRepository.save(cart);

		result.setResult(true);
		result.setMessage("");
		return result;
	}

}


























