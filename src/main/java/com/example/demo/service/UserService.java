package com.example.demo.service;

import com.example.demo.utils.TransferDataUtils;
import com.example.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserDto userDto) {
	    userRepository.save(TransferDataUtils.userDtoToEntity(userDto));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(TransferDataUtils::userEntityToDto)
                .toList();
    }

    public UserDto getUserByName(String name){
        return Optional.ofNullable(userRepository.findFirstByNameContaining(name))
                .map(TransferDataUtils::userEntityToDto)
                .orElse(null);
    }

}
