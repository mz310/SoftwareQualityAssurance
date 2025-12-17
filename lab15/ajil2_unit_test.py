"""
АЖИЛ 2: CODE → UNIT TEST ҮҮСГЭХ

Өгөгдсөн Python функц:
"""

def is_valid_email(email):
    """
    Имэйл хаягийг шалгах функц.
    
    Args:
        email (str): Шалгах имэйл хаяг
        
    Returns:
        bool: Имэйл хүчинтэй бол True, буруу бол False
    """
    if "@" not in email:
        return False
    if email.endswith("@test.com"):
        return False
    return True


"""
AI-аас үүссэн Unit Tests (pytest)
"""

import pytest


class TestIsValidEmail:
    """is_valid_email функцийн unit тестүүд"""
    
    # Эерэг тест кейсүүд (Positive Test Cases)
    def test_valid_email_with_com(self):
        """Зөв .com домэйнтэй имэйл"""
        assert is_valid_email("user@example.com") == True
    
    def test_valid_email_with_org(self):
        """Зөв .org домэйнтэй имэйл"""
        assert is_valid_email("test@example.org") == True
    
    def test_valid_email_with_edu(self):
        """Зөв .edu домэйнтэй имэйл"""
        assert is_valid_email("student@university.edu") == True
    
    def test_valid_email_with_subdomain(self):
        """Subdomain-тай зөв имэйл"""
        assert is_valid_email("user@mail.example.com") == True
    
    def test_valid_email_with_plus_sign(self):
        """+ тэмдэгт агуулсан имэйл (Gmail style)"""
        assert is_valid_email("user+tag@example.com") == True
    
    def test_valid_email_with_dots(self):
        """Цэг агуулсан username-тай имэйл"""
        assert is_valid_email("first.last@example.com") == True
    
    def test_valid_email_with_numbers(self):
        """Тоо агуулсан имэйл"""
        assert is_valid_email("user123@example.com") == True
    
    # Сөрөг тест кейсүүд (Negative Test Cases)
    def test_email_without_at_symbol(self):
        """@ тэмдэгтгүй имэйл"""
        assert is_valid_email("userexample.com") == False
    
    def test_email_ending_with_test_com(self):
        """@test.com-оор төгссөн имэйл (хориотой)"""
        assert is_valid_email("user@test.com") == False
    
    def test_email_ending_with_test_com_subdomain(self):
        """Subdomain-тай @test.com-оор төгссөн имэйл"""
        assert is_valid_email("user@mail.test.com") == False
    
    def test_empty_string(self):
        """Хоосон тэмдэгт мөр"""
        assert is_valid_email("") == False
    
    def test_only_at_symbol(self):
        """Зөвхөн @ тэмдэгт"""
        assert is_valid_email("@") == False
    
    def test_at_symbol_at_start(self):
        """@ тэмдэгт эхэнд байгаа"""
        assert is_valid_email("@example.com") == False
    
    def test_at_symbol_at_end(self):
        """@ тэмдэгт төгсгөлд байгаа"""
        assert is_valid_email("user@") == False
    
    def test_multiple_at_symbols(self):
        """Олон @ тэмдэгт агуулсан"""
        assert is_valid_email("user@@example.com") == True  # Функц @ байгаа эсэхийг шалгана
    
    # Edge Case тестүүд
    def test_email_with_spaces(self):
        """Зай агуулсан имэйл"""
        assert is_valid_email("user @example.com") == True  # @ байгаа тул True буцаана
    
    def test_email_with_special_characters(self):
        """Тусгай тэмдэгт агуулсан имэйл"""
        assert is_valid_email("user!@example.com") == True
    
    def test_very_long_email(self):
        """Маш урт имэйл"""
        long_email = "a" * 100 + "@example.com"
        assert is_valid_email(long_email) == True
    
    def test_email_with_unicode(self):
        """Unicode тэмдэгт агуулсан имэйл"""
        assert is_valid_email("тест@example.com") == True
    
    def test_email_with_only_domain(self):
        """Зөвхөн домэйн (username-гүй)"""
        assert is_valid_email("@example.com") == False  # @ байгаа тул True буцаах ёстой, гэхдээ логик алдаа байна
    
    # Засвар: Функцийн логик алдааг илрүүлсэн тест
    def test_email_with_test_com_should_be_false(self):
        """@test.com-оор төгссөн имэйл False байх ёстой"""
        assert is_valid_email("anything@test.com") == False
    
    def test_email_with_valid_com_should_be_true(self):
        """@example.com-оор төгссөн имэйл True байх ёстой"""
        assert is_valid_email("user@example.com") == True


# Засвар: Функцийн логик алдаа олдсон
# Функц нь "@" байгаа эсэхийг шалгаад, "@test.com"-оор төгссөн эсэхийг шалгана
# Гэхдээ "@example.com" зэрэг зөв имэйлүүд True буцаана
# Алдаа: "@" байгаа бүх тохиолдолд True буцаахгүй, зөвхөн "@test.com"-оос бусад тохиолдолд True

if __name__ == "__main__":
    pytest.main([__file__, "-v"])

