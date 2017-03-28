package com.example.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testAdder_1234() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder/{numbers}", numbersString));
        // Then
        ra.andExpect(status().isOk());
        ra.andExpect(content().string(String.valueOf(10)));
    }

    @Test
    public void testAdder_12345() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder/{numbers}", numbersString));
        // Then
        ra.andExpect(status().isOk());
        ra.andExpect(content().string(String.valueOf(15)));
    }

    @Test
    public void testAdder_111() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 1, 1);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder/{numbers}", numbersString));
        // Then
        ra.andExpect(content().string("오류 : 같은 수는 입력할 수 없습니다."));
        ra.andExpect(status().isBadRequest());
    }

    @Test
    public void testAdder_121() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 1);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder/{numbers}", numbersString));
        // Then
        ra.andExpect(content().string("오류 : 같은 수는 입력할 수 없습니다."));
        ra.andExpect(status().isBadRequest());
    }

    @Test
    public void testAdder_None() throws Exception {
        // Given
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder/"));
        // Then
        ra.andExpect(status().isNotFound());
    }

    @Test
    public void testAdder2_1234() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder2/{numbers}", numbersString));
        // Then
        ra.andExpect(status().isOk());
        ra.andExpect(content().string(String.valueOf(10)));
    }

    @Test
    public void testAdder2_12345() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder2/{numbers}", numbersString));
        // Then
        ra.andExpect(status().isOk());
        ra.andExpect(content().string(String.valueOf(15)));
    }

    @Test
    public void testAdder2_111() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 1, 1);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder2/{numbers}", numbersString));
        // Then
        ra.andExpect(content().string("오류 : 같은 수는 입력할 수 없습니다."));
        ra.andExpect(status().isBadRequest());
    }

    @Test
    public void testAdder2_121() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 1);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder2/{numbers}", numbersString));
        // Then
        ra.andExpect(content().string("오류 : 같은 수는 입력할 수 없습니다."));
        ra.andExpect(status().isBadRequest());
    }

    @Test
    public void testAdder2_None() throws Exception {
        // Given
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder2/"));
        // Then
        ra.andExpect(status().isNotFound());
    }

    @Test
    public void testAdder3_121() throws Exception {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 1);
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        ResultActions ra = mvc.perform(get("/calculator/adder3/{numbers}", numbersString));
        // Then
        ra.andExpect(status().isOk());  // !!!
        ra.andExpect(content().string(String.valueOf(3)));
    }

    @Test
    public void testAccumulator() throws Exception {
        testAccumulator(Arrays.asList(1, 2, 3, 4), 10);
        testAccumulator(Arrays.asList(1, 2, 3, 4, 5), 25);
    }

    private void testAccumulator(List<Integer> numbers, int actual) throws Exception {
        // Given
        final String numbersString = numbers.stream().map(String::valueOf).collect(joining(","));
        // When
        mvc.perform(get("/calculator/accumulator/{numbers}", numbersString))
                // Then
                .andExpect(status().isOk()) // 200
                .andExpect(content().string(String.valueOf(actual)));
    }
}