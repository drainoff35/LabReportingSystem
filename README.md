# Lab Reporting System

Bu proje, Java Spring kullanarak gelistirilmis bir web uygulamasidir. Uygulama, `report`, `patient`, ve `laborant` gibi varliklari iceren bir CRUD islemleri sistemini icerir. Ayrica, kullanici kimlik dogrulama ve yetkilendirme fonksiyonlari JWT ile gerceklestirilmistir.

## Gereksinimler

- Java Surumu:17
- MySql

# Kullanilan Teknolojiler
- Java Spring
- MySql
- JPA
- Maven
- Lombok
- JWT (Authentication ve Authorization icin)


## Projeyi indirme ve calistirma(IDE'ye ihtiyac duymadan)
- https://github.com/drainoff35/LabReportingSystem adresine gidip projeyi bilgisayariniza indiriniz.
- Ardindan CMD'yi acip projenin oldugu dizine gidiniz. or. **cd C:\Users\Kutay\Desktop\lab-project**
- Dogru dizinde oldugunuza emin olduktan sonra CMD'ye  **mvnw.cmd clean package** kodunu yapistiriniz.
- Bir onceki adim dogru bir sekilde tamamlandiktan sonra **java -jar target/lab-project-0.0.1-SNAPSHOT.jar** komutu ile projeyi ayaga kaldirabilirsiniz.


## MySql de bir db olusturunuz.
## Uygulamanin sorunsuz calisabilmesi icin Application.properties dosyasindaki gerekli kisimlari kendinize gore doldurunuz.
- spring.datasource.url=jdbc:mysql://localhost:3306/dbname **olusturdugunuz dbnin adi**
- spring.datasource.username=username  **kendi bilgilerinizle doldurunuz**
- spring.datasource.password=password  **kendi bilgilerinizle doldurunuz**


### API Endpoint'leri
## Uygulamanin sorunsuz bir sekilde calisabilmesi icin asagidaki tum islemleri uygun adimlarla yazacagim.
- **Projeyi baslatalim**

- **Uygulama ilk calistirildiginda ADMIN olusturacak sekilde tasarlandi**
- **Admin Rolu:** Tum CRUD operasyonlarini gerceklestirebilir.
- **Kullanici Rolu:** Tum CRUD operasyonlarini gerceklestirebilir fakat silme islemi yapamaz.

- ## POSTMAN'i acalim.
- ## Admin olarak oturum acmak icin 

- Url kismina http://localhost:8080/api/auth/signin yazip methodu GET olarak ayarlayin.
- Daha sonra Body'e tiklayip raw'i secin.
-{
    "email":"admin@admin.com",
    "password":"password"
}
- body e bunu yapistirip SEND e basin ve size verilecek olan auth token sonraki islemlerde yetki sahibi olabilmek icin kopyalayin.

- ## User olusturmak icin
- Url kismina http://localhost:8080/api/auth/signup yazip methodu POST olarak ayarlayin.
- Daha sonra Body'e tiklayip raw'i secin.
- {
    "firstName":"user",
    "lastName":"user",
    "email":"user@user.com",
    "password":"password"
}
- seklinde JSON verimizi girip SEND e basin. User olarak giris yapmak icin yukarida Admin girisini anlattigim yeri User'e gore doldurunuz.

## Herhangi bir islem yapmadan once oturum acilmalidir!

## Patient olusturalim.
- Url kismina http://localhost:8080/api/patient/create yazip methodu POST olarak ayarlayin. 
- Authorization'a tiklayip Auth Type:Bearer Token olarak ayarladiktan sonra Token kismina oturum actigimizda bize verilen tokeni yapistiralim.
## Eger diger methodlari farkli sekmede yapacaksaniz auth tokeni her yeni sekmede vermeniz gerekmektedir.
- Daha sonra Body'e tiklayip raw'i secin.
- {
    "patientIdentityNo":12345678903, **11 Haneli olmak zorunda.**
    "patientName":"patient name",
    "patientSurname":"patient surname"
}
- seklinde JSON verimizi girip SEND e basin.

- **GET http://localhost:8080/api/patient tum patient verilerini getirir**
- **GET http://localhost:8080/api/patient/{id} verilen id'ye ait patienti getirir. or.http://localhost:8080/api/patient/12345678903**
- **GET http://localhost:8080/api/patient/search isim veya soyisime gore arama yapmamizi saglar. http://localhost:8080/api/patient/search?search=a seklinde istek gonderdigimizde isminde veya soyisimden a olan patientler ciktisini aliriz.**
- **DELETE http://localhost:8080/api/patient/delete/{id} verilen id'ye ait patienti siler. http://localhost:8080/api/patient/delete/12345678903**
- **PUT http://localhost:8080/api/patient/update/{id} ile verilen id ye sahip patienti guncelleyebiliriz. Url kismina http://localhost:8080/api/patient/update/12345678903 yazip Body'den asagidaki gibi JSON verisi yollayip Send butonuna basiniz.**
- {
   "patientName":"patient name updated",
   "patientSurname":"patient surname updated"
}


## Laborant olusturalim.
- Url kismina http://localhost:8080/api/laborant/create yazip methodu POST olarak ayarlayin.
- Authorization'a tiklayip Auth Type:Bearer Token olarak ayarladiktan sonra Token kismina oturum actigimizda bize verilen tokeni yapistiralim.
## Eger diger methodlari farkli sekmede yapacaksaniz auth tokeni her yeni sekmede vermeniz gerekmektedir.
- Daha sonra Body'e tiklayip raw'i secin.
- {
    "laborantId":1234567, **7 Haneli olmak zorunda.**
    "laborantName":"laborant name",
    "laborantSurname":"laborant surname"
}
- seklinde JSON verimizi girip SEND e basin.
- **GET http://localhost:8080/api/laborant tum laborant verilerini getirir**
- **GET http://localhost:8080/api/laborant/{id} verilen id'ye ait laboranti getirir. or.http://localhost:8080/api/laborant/1234567**
- **GET http://localhost:8080/api/laborant/search isim veya soyisime gore arama yapmamizi saglar. http://localhost:8080/api/laborant/search?search=a seklinde istek gonderdigimizde isminde veya soyisimden a olan laborantlar ciktisini aliriz.**
- **DELETE http://localhost:8080/api/laborant/delete/{id} verilen id'ye ait laboranti siler. http://localhost:8080/api/laborant/delete/1234567**
- **PUT http://localhost:8080/api/patient/update/{id} ile verilen id ye sahip patienti guncelleyebiliriz. Url kismina http://localhost:8080/api/patient/update/12345678903 yazip Body'den asagidaki gibi JSON verisi yollayip Send butonuna basiniz.**
- {
   "laborantName":"laborant name updated",
   "laborantSurname":"laborant surname updated"
}

## Report olusturalim
## Report olusturabilmemiz icin laborant ve patient verilerimizin olmasi gerekmektedir.
- Url kismina http://localhost:8080/api/report/create yazip methodu POST olarak ayarlayin.
- Authorization'a tiklayip Auth Type:Bearer Token olarak ayarladiktan sonra Token kismina oturum actigimizda bize verilen tokeni yapistiralim.
## Eger diger methodlari farkli sekmede yapacaksaniz auth tokeni her yeni sekmede vermeniz gerekmektedir.
- Daha sonra Body'e tiklayip form-data'yi secin.
- Report olustururken Report ve Reportun imagesi olacagi icin image yollayacagiz.
- form-data'da 
    key: report 
    type:text 
    Value:
    {
   "diagnosticTitle": "diagnostic title",
    "diagnosticDescription": "diagnostic description",
    "patient": {
        "patientIdentityNo": 12345678903  
        },
    "laborant": {
        "laborantId": 1234567
        }
    }

- form-data'da 
    key: photo
    type:file
    value: bilgisayarinizdan reporta ait fotografi secin.
- seklinde verilerimizi girip SEND e basin.

- **GET http://localhost:8080/api/report tum report verilerini getirir**
- **GET http://localhost:8080/api/report/{id} verilen id'ye ait reportu getirir. or.http://localhost:8080/api/laborant/1**
- **DELETE http://localhost:8080/api/report/delete/{id} verilen id'ye ait reportu siler. http://localhost:8080/api/report/delete/1**
- **PUT http://localhost:8080/api/report/update/{id} ile verilen id ye sahip reportu guncelleyebiliriz. Url kismina http://localhost:8080/api/report/update/1 yazip Body'den asagidaki gibi JSON verisi yollayip Send butonuna basiniz.**
- {
    "diagnosticTitle":"updated title",
    "diagnosticDescription":"updated description",
    "laborant":{
        "laborantId":1234567
    }
}

- **GET http://localhost:8080/api/report/details/{id} verilen id'ye sahip raporun detaylarini getirir. or. http://localhost:8080/api/report/details/1**


- Proje hakkinda herhangi bir sorunuz olursa **https://www.linkedin.com/in/kutaycetin35** adresinden bana ulasabilirsiniz.






















