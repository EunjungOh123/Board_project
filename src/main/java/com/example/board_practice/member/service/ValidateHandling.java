package com.example.board_practice.member.service;

import org.springframework.validation.Errors;

import java.util.Map;

public interface ValidateHandling {
    Map<String, String> validateHandling(Errors errors);
}
