package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * С помощью данного класса мы сообщаем клиенту информацию об ощибках
 * Используется в глобальном обрабочике ошибок RestErrorHandler
 */
@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
}
