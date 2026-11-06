<div align="center">

# 🌌 2048 Cyber Blue Edition (Java OOP)

**Dark Blue Gradient | Neon Glow | Silliq Animatsiyalar | OOP Arxitekturasi**

[![Java](https://img.shields.io/badge/Java-21%2B-007396?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Theme](https://img.shields.io/badge/Theme-Dark_Blue_Gradient-2563eb?style=for-the-badge)](https://github.com/Rajabaliyev01/java-game-2048)
[![GUI](https://img.shields.io/badge/GUI-Java_Swing_2D-38bdf8?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![Architecture](https://img.shields.io/badge/Architecture-OOP_/_MVC-10b981?style=for-the-badge)](#-oop-tamoyillari-qanday-qollangan)
[![AI Assisted](https://img.shields.io/badge/Built_with-Antigravity_AI-8b5cf6?style=for-the-badge)](https://deepmind.google)

---

*Java tilini 0 dan o'rganuvchilar uchun qiziqarli, zamonaviy va real natijali amaliyot sifatida **Antigravity AI** yordamida to'liq **OOP (Object-Oriented Programming)** tamoyillari asosida tayyorlangan 2048 o'yini.*

</div>

---

## ✨ Zamonaviy Dizayn va Imkoniyatlar

| Xususiyat | Tavsif |
|-----------|--------|
| 🌌 **Dark Blue Gradient Fon** | Midnight Blue (`#0a0f1d` -> `#0f172a`) rangidagi zamonaviy chuqur gradient dizayn |
| 💎 **Glassmorphism & Neon Glow** | Har bir katakchada yorug'lik refleksi (glass shine), nafis neon porlash va yumaloqlashtirilgan burchaklar |
| 🎬 **Pop-in Animatsiya** | Yangi paydo bo'ladigan har bir katakcha silliq 60 FPS kattalashib (scale animation) chiqadi |
| 🎯 **Klassik 2048 Mexanikasi** | 4x4 doska, plitkalarning siljishi, bir xil raqamlarning birlashishi va tasodifiy 2/4 kataklar |
| 📊 **Cyber Score Kartochkasi** | Glassmorphism uslubidagi ballar paneli, shisha effekti va real vaqt hisobi |
| ⌨️ **Ergonomik Boshqaruv** | **WASD** va Klaviaturadagi **Strelkalar (↑, ↓, ←, →)** orqali boshqarish, **R** bilan bir zumda qayta boshlash |
| 🏆 **G'alaba va Game Over** | 2048 ga erishilganda tabrik xabarnomasi, imkoniyat tugaganda esa qizil neonli zamonaviy Game Over overlay |

---

## 🎨 Ranglar Palitrasi (Cyber Dark Blue Spectrum)

- `2 / 4`: Royal & Electric Blue (`#1e3a8a` → `#2563eb`)
- `8 / 16`: Sky Blue & Cyan (`#0ea5e9` → `#06b6d4`)
- `32 / 64`: Teal & Zumrad (`#14b8a6` → `#10b981`)
- `128 / 256`: Indigo & Violet (`#6366f1` → `#8b5cf6`)
- `512 / 1024`: Purple & Fuchsia (`#a855f7` → `#d946ef`)
- `2048`: Cyber Cyan Porlash (`#00f5d4` + Neon Aura)

---

## 🧠 OOP Tamoyillari Qanday Qo'llangan?

Loyiha arxitekturasi **Model-View-Controller (MVC)** tamoyiliga to'liq mos keladi:

```
src/game2048/
├── Main.java              # Dasturga kirish nuqtasi
├── model/
│   ├── Tile.java          # Katakcha modeli: qiymatlar, gradientlar va porlash rangi (Encapsulation)
│   ├── Direction.java     # Harakat yo'nalishlari xavfsiz boshqaruvi (Enum)
│   └── Board.java         # Doska logikasi, matematika va animatsiya koordinatalari
└── ui/
    └── GamePanel.java     # Swing Graphics2D dizayn, 60 FPS taymer va klaviatura boshqaruvi
```

---

## 🚀 Ishga Tushirish

### 1. Repozitoriyani klonlash
```bash
git clone https://github.com/Rajabaliyev01/java-game-2048.git
cd java-game-2048
```

### 2. Ishga tushirish (macOS / Linux)
```bash
./run.sh
```

Yoki qo'lda:
```bash
javac -d bin src/game2048/model/*.java src/game2048/ui/*.java src/game2048/Main.java
java -cp bin game2048.Main
```

---

## 🎮 Boshqaruv Qoidalari

| Tugma | Vazifasi |
|-------|----------|
| `↑` yoki `W` | Yuqoriga surish |
| `↓` yoki `S` | Pastga surish |
| `←` yoki `A` | Chapga surish |
| `→` yoki `D` | O'ngga surish |
| `R` | O'yinni yangilash (Restart) |

---

## 🤖 AI Yordami

> Ushbu loyiha **Antigravity AI** yordamida yangi boshlovchi dasturchiga Java imkoniyatlari, Swing grafikasi va OOP tamoyillarini ko'rgazmali va zavqli tarzda o'rgatish maqsadida yaratildi.

---

## 👨‍💻 Muallif

**Javohir Rajabaliyev** — [github.com/Rajabaliyev01](https://github.com/Rajabaliyev01)
