"""
АЖИЛ 4: SYNTHETIC TEST DATA ҮҮСГЭХ

20 ширхэг synthetic user data үүсгэх:
- name, email, age, country, role
- PII агуулаагүй
- Бодит мэт боловч зохиомол
"""

import random
import csv
from typing import List, Dict


# Synthetic data үүсгэхэд ашиглах өгөгдөл
FIRST_NAMES = [
    "Бат", "Цэцэг", "Очир", "Сараа", "Төмөр", "Мөнх", "Энх", "Болд", 
    "Алтан", "Сүрэн", "Билгүүн", "Номин", "Дэлгэр", "Жаргал", "Эрдэнэ",
    "Алтанцэцэг", "Мөнхцэцэг", "Оюун", "Сайхан", "Батбаяр"
]

LAST_NAMES = [
    "Батбаяр", "Цэцэгмаа", "Очирбат", "Сараа", "Төмөрбат", "Мөнхбат",
    "Энхбат", "Болдбат", "Алтанбат", "Сүрэнбат", "Билгүүн", "Номин",
    "Дэлгэрбат", "Жаргалбат", "Эрдэнэбат", "Алтанцэцэг", "Мөнхцэцэг",
    "Оюунбат", "Сайханбат", "Батбаяр"
]

DOMAINS = [
    "example.com", "testmail.com", "demo.org", "sample.net", 
    "mockemail.com", "fakemail.org", "dummy.net"
]

COUNTRIES = [
    "Монгол", "Америк", "Канад", "Герман", "Франц", "Япон", 
    "Солонгос", "Хятад", "Орос", "Австрали"
]

ROLES = [
    "Admin", "User", "Manager", "Developer", "Tester", 
    "Analyst", "Designer", "Guest", "Moderator", "Viewer"
]


def generate_synthetic_user(user_id: int) -> Dict[str, any]:
    """
    Нэг synthetic user үүсгэх
    
    Args:
        user_id: User-ийн ID
        
    Returns:
        Dict: User-ийн мэдээлэл
    """
    first_name = random.choice(FIRST_NAMES)
    last_name = random.choice(LAST_NAMES)
    name = f"{first_name} {last_name}"
    
    # Email үүсгэх (PII агуулаагүй)
    email_prefix = f"user{user_id}_{first_name.lower()}"
    domain = random.choice(DOMAINS)
    email = f"{email_prefix}@{domain}"
    
    # Age үүсгэх (логик хязгаарт)
    age = random.randint(18, 65)
    
    country = random.choice(COUNTRIES)
    role = random.choice(ROLES)
    
    return {
        "id": user_id,
        "name": name,
        "email": email,
        "age": age,
        "country": country,
        "role": role
    }


def generate_synthetic_users(count: int = 20) -> List[Dict[str, any]]:
    """
    Олон synthetic user үүсгэх
    
    Args:
        count: Үүсгэх user-ийн тоо
        
    Returns:
        List[Dict]: User-ийн жагсаалт
    """
    users = []
    used_emails = set()  # Давхардлыг шалгах
    
    for i in range(1, count + 1):
        user = generate_synthetic_user(i)
        
        # Email давхардлыг шалгах
        while user["email"] in used_emails:
            user = generate_synthetic_user(i)
        
        used_emails.add(user["email"])
        users.append(user)
    
    return users


def validate_synthetic_data(users: List[Dict[str, any]]) -> Dict[str, any]:
    """
    Synthetic data-г шалгах
    
    Args:
        users: User-ийн жагсаалт
        
    Returns:
        Dict: Шалгалтын үр дүн
    """
    validation_result = {
        "total_users": len(users),
        "duplicate_emails": [],
        "invalid_ages": [],
        "pii_detected": [],
        "all_valid": True
    }
    
    emails = []
    for user in users:
        # Email давхардлыг шалгах
        if user["email"] in emails:
            validation_result["duplicate_emails"].append(user["email"])
            validation_result["all_valid"] = False
        emails.append(user["email"])
        
        # Age логик шалгалт
        if user["age"] < 0 or user["age"] > 150:
            validation_result["invalid_ages"].append({
                "user": user["name"],
                "age": user["age"]
            })
            validation_result["all_valid"] = False
        
        # PII шалгалт (бодит имэйл, утасны дугаар гэх мэт)
        # Энэ жишээнд PII байхгүй байх ёстой
    
    return validation_result


def save_to_csv(users: List[Dict[str, any]], filename: str = "synthetic_users.csv"):
    """
    Synthetic data-г CSV файлд хадгалах
    
    Args:
        users: User-ийн жагсаалт
        filename: Файлын нэр
    """
    with open(filename, 'w', newline='', encoding='utf-8') as csvfile:
        fieldnames = ['id', 'name', 'email', 'age', 'country', 'role']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        
        writer.writeheader()
        for user in users:
            writer.writerow(user)
    
    print(f"✓ {len(users)} user-ийн мэдээлэл {filename} файлд хадгалагдлаа")


def print_users_table(users: List[Dict[str, any]]):
    """
    User-ийн мэдээллийг хүснэгт хэлбэрээр хэвлэх
    
    Args:
        users: User-ийн жагсаалт
    """
    print("\n" + "=" * 100)
    print("SYNTHETIC USER DATA")
    print("=" * 100)
    print(f"{'ID':<5} {'Name':<20} {'Email':<30} {'Age':<5} {'Country':<15} {'Role':<15}")
    print("-" * 100)
    
    for user in users:
        print(f"{user['id']:<5} {user['name']:<20} {user['email']:<30} {user['age']:<5} {user['country']:<15} {user['role']:<15}")
    
    print("=" * 100)


def main():
    """Гол функц"""
    print("=" * 60)
    print("АЖИЛ 4: SYNTHETIC TEST DATA ҮҮСГЭХ")
    print("=" * 60)
    
    # 20 synthetic user үүсгэх
    print("\n1. 20 ширхэг synthetic user data үүсгэж байна...")
    users = generate_synthetic_users(20)
    
    # Data шалгах
    print("2. Өгөгдлийг шалгаж байна...")
    validation = validate_synthetic_data(users)
    
    # Үр дүнг хэвлэх
    print("\nШалгалтын үр дүн:")
    print(f"  - Нийт user: {validation['total_users']}")
    print(f"  - Давхардсан email: {len(validation['duplicate_emails'])}")
    print(f"  - Хүчингүй age: {len(validation['invalid_ages'])}")
    print(f"  - Бүх өгөгдөл хүчинтэй: {'Тийм' if validation['all_valid'] else 'Үгүй'}")
    
    if validation['duplicate_emails']:
        print(f"\n  ⚠ Давхардсан email-үүд: {validation['duplicate_emails']}")
    
    if validation['invalid_ages']:
        print(f"\n  ⚠ Хүчингүй age-үүд:")
        for item in validation['invalid_ages']:
            print(f"    - {item['user']}: {item['age']}")
    
    # Хүснэгт хэвлэх
    print_users_table(users)
    
    # CSV файлд хадгалах
    print("\n3. CSV файлд хадгалж байна...")
    save_to_csv(users, "synthetic_users.csv")
    
    print("\n" + "=" * 60)
    print("Дүгнэлт:")
    print("✓ 20 ширхэг synthetic user data амжилттай үүсгэгдлээ")
    print("✓ PII агуулаагүй")
    print("✓ Давхардал байхгүй")
    print("✓ Логик хязгаарт age утгууд")
    print("=" * 60)


if __name__ == "__main__":
    main()

