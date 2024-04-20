package com.example.cv_generator.service.Impl;

import com.example.cv_generator.config.AppConstants;
import com.example.cv_generator.dto.UserDto;
import com.example.cv_generator.entity.Role;
import com.example.cv_generator.entity.User;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.RoleRepository;
import com.example.cv_generator.repository.UserRepository;
import com.example.cv_generator.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = modelMapper.map(userDto,User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role=roleRepository.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = userRepository.save(user);

        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=dtoToUser(userDto);
        User savedUser=userRepository.save(user);
        return userToDto(savedUser);
    }


    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        UserDto userDto1= userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        List<UserDto> userDtos= users.stream().map(user -> userToDto(user)).toList();
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepository.delete(user);
    }
    public User dtoToUser(UserDto userDto){
        //ModelMapper is used to convert one class object to another class object
        User user = modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }
    public UserDto userToDto(User user){
        UserDto userDto=modelMapper.map(user,UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
