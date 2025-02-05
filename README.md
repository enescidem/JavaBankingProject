# Banking Project (Java)
 




https://github.com/user-attachments/assets/0c70032b-bde7-4346-a9cc-1df1f55df9c7


# Suwi Bank - Banka Yönetim Sistemi 

Bu proje, kullanıcıların bankacılık işlemlerini güvenli ve kolay bir şekilde gerçekleştirebileceği bir masaüstü bankacılık uygulamasıdır.  

## 🚀 Projenin Amacı  

Sistem, kullanıcıların aşağıdaki işlemleri gerçekleştirmesini sağlar:  

✅ TC Kimlik No ve şifre ile güvenli giriş yapma  
✅ Yeni hesap oluşturma  
✅ Şifre sıfırlama işlemleri  
✅ Hesap bakiyesini görüntüleme  
✅ Para yatırma ve çekme işlemleri  
✅ Başka bir hesaba havale yapma  
✅ Güncel döviz kurlarını görüntüleme  
✅ İşlem geçmişini görüntüleyip **PDF** olarak kaydetme  
✅ Kişisel bilgileri güncelleme ve hesap silme  

## 🛠 Kullanılan Teknolojiler  

- **🖥 Programlama Dili:** Java  
- **🎨 Arayüz:** Swing GUI  
- **🗄 Veritabanı:** MySQL  
- **🔗 Bağlantı:** JDBC  
- **💻 Geliştirme Ortamı:** Eclipse IDE

## 🚀 Kurulum ve Başlangıç

1. **XAMPP Kontrol Panelini Açın**
   - **Apache** ve **MySQL** servislerini başlatın.
   - "Admin" butonuna tıklayarak phpMyAdmin'i açın.

2. **Veritabanı ve Tablolar**
   - Veritabanında **iki tablo** bulunmaktadır:
     - `girissayfasi` → Kullanıcı bilgilerini içerir (HesapNo, TcKimlikNo, Ad, Soyad, Telefon, Şifre, Bakiye).
     - `hareketdokumu` → Kullanıcı işlemlerini takip eder (HareketDokumu_id, HesapNo, İşlem, Tarih).
   - `girissayfasi` tablosunun **primary key** değeri `HesapNo`, `hareketdokumu` tablosunun **primary key** değeri `HareketDokumu_id` olarak belirlenmiştir.

3. **Proje Çalıştırma**
   - **Eclipse IDE** üzerinden projeyi başlatın.
   - Açılan uygulama **SUWİ BANK** arayüzüyle karşılaşacaksınız.

---

## 📌 Uygulama Akışı

### 1️⃣ **Giriş Sayfası**
- Kullanıcılar **TC Kimlik No** ve **Şifre** girerek giriş yapabilir.
- "Giriş Yap" butonuna basıldığında bilgiler kontrol edilir ve doğruysa **Hesap Ekranı** açılır.
- Ayrıca **"Yeni Hesap Aç"** ve **"Şifremi Unuttum"** butonları da bulunmaktadır.
- Şifre alanında bir "göz" simgesi bulunur; tıklayarak şifreyi görebilirsiniz.
- Hatalı bilgiler girildiğinde **"Hatalı TC Kimlik No veya Şifre"** uyarısı gösterilir.

### 2️⃣ **Hesap Ekranı**
- Kullanıcı adı, soyadı ve hesap numarası ekranda gösterilir.
- **8 farklı işlem butonu** bulunmaktadır:
  1. **Bakiye Görüntüleme** → Güncel bakiyeyi gösterir.
  2. **Para Yatırma** → Kullanıcıdan tutar alarak bakiyeye ekler.
  3. **Para Çekme** → Kullanıcıdan tutar alarak bakiyeden düşer.
  4. **Havale İşlemi** → Başka bir hesaba para gönderir.
  5. **Döviz Kurları** → Güncel döviz kurlarını gösterir.
  6. **İşlem Geçmişi** → Yapılan tüm işlemleri listeler.
  7. **Ayarlar** → Telefon numarası ve şifre güncellenebilir.
  8. **Çıkış Yap** → Hesaptan güvenli çıkış sağlar.

### 3️⃣ **Yeni Hesap Açma**
- "Yeni Hesap Aç" butonuna basarak yeni kullanıcı kaydı oluşturabilirsiniz.
- **Gerekli Bilgiler:** TC Kimlik No, Ad, Soyad, Telefon No, Şifre.
- Kayıt başarıyla tamamlandığında "Başarıyla Hesap Oluşturuldu" mesajı gösterilir.

### 4️⃣ **Para Yatırma ve Çekme**
- "Para Yatır" butonuna basarak girilen tutarı ekleyebilirsiniz.
- "Para Çek" butonuna basarak bakiyeden belirlenen tutarı çekebilirsiniz.
- Bakiye 0 TL olduğunda para çekme işlemi yapılamaz.

### 5️⃣ **Havale İşlemi**
- Havale ekranında alıcı hesap bilgileri girilir (Ad, Soyad, Hesap No, Tutar).
- Hatalı bilgi girildiğinde işlem iptal edilir.
- İşlem başarıyla tamamlandığında **"X TL Enes Çidem'e Gönderildi. Kalan Bakiye: Y TL"** mesajı gösterilir.

### 6️⃣ **İşlem Geçmişi ve PDF Yazdırma**
- Yapılan tüm işlemler (bakiye sorgulama, havale, para yatırma vb.) işlem geçmişinde listelenir.
- "PDF Olarak Yazdır" butonu ile hareket dökümü **PDF formatında** oluşturulur ve yazdırılabilir.

### 7️⃣ **Döviz Kurları**
- Güncel döviz fiyatlarını gösteren bir ekran açılır.
- "Yenile" butonuna basarak güncel verileri alabilirsiniz.

### 8️⃣ **Ayarlar Menüsü**
- Telefon numarası ve şifre güncellenebilir.
- "Hesabı Sil" butonu ile hesap **kalıcı olarak** silinebilir.

### 9️⃣ **Şifremi Unuttum**
- TC Kimlik No, Ad, Soyad ve Telefon No bilgileri girilerek yeni şifre belirlenebilir.
- Bilgiler eşleşmezse hata mesajı alınır.
- Başarılı olduğunda "Şifre Başarıyla Güncellendi" mesajı gösterilir.

---

## 📢 Sonuç
Bu proje, **banka işlemlerini simüle eden** bir Java uygulamasıdır. Kullanıcı dostu arayüzü ve MySQL ile entegre yapısıyla **kayıt işlemleri, para transferleri ve güvenli giriş mekanizmalarını** başarıyla sunmaktadır. Daha fazla detay için **videoyu izleyebilir veya kodları inceleyebilirsiniz.**

✅ **Geliştirici Notu:** Geri bildirimleriniz benim için değerlidir, lütfen paylaşın! 😊


