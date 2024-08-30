package com.example.springbootdemo.response;

import com.example.springbootdemo.model.User;

import java.util.List;

public record UserListResponse(List<User> users, long totalCount) {
}