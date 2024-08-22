package acorn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acorn.shop.dto.CustomerDto;
import acorn.shop.entity.Customer;
import acorn.shop.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // 회원가입
    public void registerCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
            .id(customerDto.getId())
            .name(customerDto.getName())
            .password(customerDto.getPassword()) // 암호화 제거
            .email(customerDto.getEmail())
            .phoneNumber(customerDto.getPhoneNumber())
            .address(customerDto.getAddress())
            .detailAddress(customerDto.getDetailAddress())
            .build();
        customerRepository.save(customer);
    }

    // 로그인 검증
    public boolean validateLogin(String id, String password) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return password.equals(customer.getPassword()); // 암호화 제거
        }
        return false;
    }

    // ID 중복 확인
    public boolean isIdDuplicate(String id) {
        return customerRepository.existsById(id);
    }
}
