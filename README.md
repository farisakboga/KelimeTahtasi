# Kelime Tahtası – Android WebView

Bu proje mevcut `index.html` uygulamasını çevrimdışı çalışan bir Android WebView içinde açar.

Android'in durum ve gezinme çubukları için güvenli alan bırakılır; uygulamanın üst ve alt düğmeleri sistem çubuklarının altında kalmaz. `kelimelerim.png` uygulama simgesi olarak kullanılır.

## Fiziksel tuşlar

- Ses kısma: sonraki kelime
- Ses açma: önceki kelime
- Her basış yalnızca bir kelime ilerletir veya geri götürür.
- Uygulama açıkken bu iki tuş sistem sesini değiştirmez.

## APK oluşturma

### Android Studio kurmadan GitHub ile

1. GitHub'da yeni ve boş bir depo oluşturun.
2. Bu klasörün **içindeki bütün dosya ve klasörleri** deponun ana dizinine yükleyin. `.github` klasörünün de yüklendiğinden emin olun.
3. GitHub'da `Actions` sekmesini açın ve gerekirse iş akışlarını etkinleştirin.
4. Soldan `APK Oluştur` iş akışını seçin.
5. `Run workflow` düğmesine, ardından açılan penceredeki ikinci `Run workflow` düğmesine basın.
6. İşlem tamamlanıp yeşil onay işareti görününce çalışmayı açın.
7. Sayfanın altındaki `Artifacts` bölümünden `Kelime-Tahtasi-APK` dosyasını indirin.
8. İnen ZIP'i açın; kurulacak dosya `app-debug.apk` olacaktır.

`main` veya `master` dalına yeni dosya gönderildiğinde APK otomatik olarak yeniden oluşturulur.

### Android Studio ile

1. `KelimeTahtasi-Android` klasörünü Android Studio ile açın.
2. Gradle eşitlemesinin tamamlanmasını bekleyin.
3. `Build > Build Bundle(s) / APK(s) > Build APK(s)` yolunu izleyin.
4. Oluşan APK `app/build/outputs/apk/debug/app-debug.apk` konumundadır.

Uygulama dikey ekran yönüne sabitlenmiştir ve ekranın sunum sırasında kapanmasını engeller.
