package com.example.personalproject.product.service;

import com.example.personalproject.member.ServiceResult;
import com.example.personalproject.product.dto.CartDto;
import com.example.personalproject.product.entity.Cart;
import com.example.personalproject.product.mapper.CartMapper;
import com.example.personalproject.product.model.CartParam;
import com.example.personalproject.product.model.OrderStatus;
import com.example.personalproject.product.repository.CartRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

	@Override
	public List<CartDto> list(CartParam parameter) {
		long totalCount = cartMapper.selectListCount(parameter);

		List<CartDto> list = cartMapper.selectList(parameter);
		if (!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for (CartDto x : list) {
				x.setTotalCount(totalCount);
				x.setSeq(totalCount - parameter.getPageStart() - i);
				i++;
			}
		}

		return list;
	}

	@Override
    public CartDto detail(long id) {
        
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()) {
            return CartDto.of(optionalCart.get());
        }
        return null;
    }
    
    @Override
    public ServiceResult updateStatus(long cartId, OrderStatus orderStatus) {
        
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (!optionalCart.isPresent()) {
            return new ServiceResult(false, "상품 정보가 존재하지 않습니다.");
        }
        
        Cart cart = optionalCart.get();
        
        cart.setOrderStatus(orderStatus);
        cartRepository.save(cart);
        
        return new ServiceResult(true);
    }

    
    @Override
    public ServiceResult cancel(long cartId) {
    
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()) {
            return new ServiceResult(false, "상품 정보가 존재하지 않습니다.");
        }
        
        Cart cart = optionalCart.get();
        
        cart.setOrderStatus(OrderStatus.CANCEL);
        cartRepository.save(cart);
        
        return new ServiceResult();
    }
}


























