<div align="center">

# 🎮 2048 Game (Java OOP)

**Obyektga Yo'naltirilgan Dasturlash (OOP) | Java Swing GUI | Toza Arxitektura**

[![Java](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![GUI](https://img.shields.io/badge/GUI-Java_Swing-007396?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Architecture](https://img.shields.io/badge/Architecture-OOP_/_MVC-brightgreen?style=for-the-badge)](#-oop-tamoyillari-qanday-qollangan)
[![AI Assisted](https://img.shields.io/badge/Built_with-Antigravity_AI-blueviolet?style=for-the-badge)](https://deepmind.google)

---

*Ushbu loyiha Java dasturlash tilini 0 dan o'rganuvchilar uchun qiziqarli va real natijali amaliyot sifatida **Antigravity AI** yordamida to'liq **OOP (Object-Oriented Programming)** tamoyillari asosida yaratildi.*

</div>

---

## ✨ Asosiy Imkoniyatlar

| Xususiyat | Tavsif |
|-----------|--------|
| 🎯 **Klassik 2048 O'yin Logikasi** | 4x4 doska, harakatlar bo'yicha siljish, bir xil sonlarning birlashishi va tasodifiy 2/4 kataklar paydo bo'lishi |
| 🎨 **Zamonaviy Grafik Interfeys** | Java Swing Graphics2D orqali chizilgan yumshoq burchakli (rounded) kataklar, asl 2048 o'yinining rasmiy ranglar palitrasi |
| 📊 **Hisob (Score) Doskasi** | Birlashtirilgan har bir raqam ball sifatida yig'iladi va real vaqtda ko'rsatiladi |
| ⌨️ **Ko'p Qirrali Boshqaruv** | Klaviaturadagi Strelkalar (↑, ↓, ←, →) yoki geymerlar uchun qulay **W, A, S, D** tugmalari |
| 🔄 **Tezkor Qayta Boshlash** | O'yin maydoni to'lganda yoki xohlagan paytda `R` tugmasi orqali o'yinni bir zumda qayta boshlash |
| 🏆 **G'alaba va Mag'lubiyat Xabarnomasi** | 2048 raqamiga erishilganda tabrik oynasi va harakat qolmaganda chiroyli Game Over ekrani |

---

## 🧠 OOP Tamoyillari Qanday Qo'llangan?

Loyiha arxitekturasi **Model-View-Controller (MVC)** konsepsiyasiga tayangan:

```
src/game2048/
├── Main.java              # Kirish nuqtasi (Entry Point)
├── model/
│   ├── Tile.java          # Katakcha modeli (Encapsulation)
│   ├── Direction.java     # Harakat yo'nalishlari (Enum)
│   └── Board.java         # Doska va o'yin qoidalari logikasi
└── ui/
    └── GamePanel.java     # Foydalanuvchi interfeysi (Swing View + Key Listeners)
```

1. **Inkapsulyatsiya (Encapsulation):**
   - `Tile` sinfida har bir katakning qiymati (`value`) yashirilgan (`private`). Har bir raqam (2, 4, 8, 16...) o'zining fon va matn ranglarini mustaqil boshqaradi.
   - `Board` sinfida o'yin doskasi holati (`grid`) tashqi o'zgarishlardan himoyalangan, harakatlar esa faqat `move(Direction)` metodi orqali xavfsiz amalga oshiriladi.
2. **Abstraksiya va Ma'suliyatlarni Ajratish (Separation of Concerns):**
   - O'yin matematikasi (`Board.java`) foydalanuvchi interfeysidan (`GamePanel.java`) mutlaqo ajratilgan. Bu kodni o'qishni va kengaytirishni osonlashtiradi.
3. **Enum bilan Xavfsiz Tiplash (Type Safety):**
   - `Direction` enumi harakat yo'nalishlarini qat'iy tekshiradi (`UP`, `DOWN`, `LEFT`, `RIGHT`), bu xatoliklarni oldini oladi.

---

## 🚀 Ishga Tushirish

### 1. Repozitoriyani klonlash
```bash
git clone https://github.com/Rajabaliyev01/java-game-2048.git
cd java-game-2048
```

### 2. Bitta buyruq bilan ishga tushirish (macOS / Linux)
```bash
./run.sh
```

### Yoki qo'lda kompyatsiya qilish va ishga tushirish:
```bash
javac -d bin src/game2048/model/*.java src/game2048/ui/*.java src/game2048/Main.java
java -cp bin game2048.Main
```

---

## 🎮 Boshqaruv Qoidalari

| Tugma | Harakat |
|-------|---------|
| `↑` yoki `W` | Katakchalarni yuqoriga surish |
| `↓` yoki `S` | Katakchalarni pastga surish |
| `←` yoki `A` | Katakchalarni chapga surish |
| `→` yoki `D` | Katakchalarni o'ngga surish |
| `R` | O'yinni yangidan boshlash (Restart) |

---

## 🤖 AI Yordami

> Ushbu loyiha **Antigravity AI** yordamida, yangi boshlovchi dasturchiga Java imkoniyatlarini hamda OOP qoidalarini real amaliy loyihada ko'rsatish maqsadida ishlab chiqildi.

---

## 👨‍💻 Muallif

**Javohir Rajabaliyev** — [github.com/Rajabaliyev01](https://github.com/Rajabaliyev01)

> *"Dasturlashni o'rganishning eng yaxshi yo'li — real narsalarni yasashdir! 🚀"*
