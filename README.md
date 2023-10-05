# Tugas Besar Algeo I ( JUN HOK 88 )
> Tugas besar ini disusun sebaik-baiknya oleh anggota kelompok Jun Hok 88 untuk memenuhi tugas mata kuliah Aljabar Linier dan Geometri (IF2123) K01.

## Ringkasan Komposisi Repository
Program pada repository ini berisi program yang terdiri dari folder _src_, _bin_, _doc_, dan _test_.
1. Folder **src** berisi source code yang dibuat oleh anggota kelompok kami yang terdiri dari `Matrix.java` dan `Main.java`. Segala jenis fungsi dan prosedur yang digunakan untuk memproses matriks untuk menghasilkan hasil sesuai dengan operasi yang digunakan terdapat pada `Matrix.java`. Sedangkan, `Main.java` berisi program utama yang dapat dijalankan oleh user dan menjadi User Interface (UI) bagi user. `Main.java` telah diintegrasi dengan `Matrix.java` sehingga dapat dilihat cara penggunaan dan mekanisme penggunaan function dan prosedur yang terdapat pada `Matrix.java`.
2. Folder **bin** memuat class hasil compile dari:
    - `Main.class` adalah Bytecode Java dari `Main.java` yang berkaitan dengan program utama.
    - `Matrix.class` adalah Bytecode Java dari `Matrix.java` yang berkaitan dengan fungsi atau prosedur dalam pemrosesan operasi matriks.
    - `Additional.class` adalah Bytecode Java dari `Additional.java` yang berkaitan dengan animasi yang dikeluarkan saat keluar dari program utama.
3. Folder **doc** memuat dokumen laporan hasil pengerjaan tugas besar ini, yang terdiri dari deskripsi masalah, teori singkat, implementasi, eksperimen, dan kesimpulan.
4. Folder **test** memuat testcase yang terdapat pada studi kasus spesifikasi yang telah diberikan oleh asisten dan di dalam terdapat folder **out** yang menjadi tempat keluaran program dalam bentuk file jika user meminta (format .txt).
   
## Informasi
Repositori ini mengandung program yang diperlukan untuk memenuhi Tugas Besar 1 IF2123 Aljabar Linier dan Geometri. Kami membuatnya dalam bahasa Java untuk menyelesaikan persoalan matriks. Yang menjadi anggota dari kelompok JUN HOK 88 adalah sebagai berikut.
- 13522045 Elbert Chailes
- 13522051 Kharris Khisunica
- 13522055 Benardo

## Fitur
- Mencari solusi dan menyelesaikan permasalahan Sistem Persamaan Linear (SPL) dengan beberapa pilihan metode, seperti metode eliminasi Gauss, metode eliminasi Gauss-Jordan, metode matriks invers, dan metode _cramer_.
- Mengeluarkan hasil balikan dari sebuah matriks dengan menggunakan metode Gauss-Jordan yang dipadukan dengan matriks identitas (AI|IA^-1) dan metode adjoin matriks.
- Menghitung dan menentukan nilai determinan dari sebuah matriks dengan menggunakan metode reduksi baris dan metode matriks balikan
- Menyelesaikan permasalahan interpolasi polinomial
- Menyelesaikan permasalahan regresi linear berganda
- Menyelesaikan permasalahan _bicubic spline interpolation_

## Bahasa yang digunakan
- Java

## Tata Cara Penggunaan Program
Pertama, lakukan pengambilan program dari github untuk kemudian dipindahkan ke _local computer_ yang digunakan oleh user dengan menggunakan git clone seperti mekanisme yang terlihat sebagai berikut.
```shell
git clone git@github.com:ChaiGans/Algeo01-22045.git
```
Kemudian, pastikan masuk ke dalam folder yang telah di-_clone_, biasanya akan secara default bernama Algeo01-22045.

### Kasus I : Jika anda adalah user dan hanya ingin menggunakan program tanpa modifikasi lebih lanjut terkait algoritma kode yang dimiliki
Jika directory anda sudah berada pada folder "Algeo01-22045" tersebut maka lakukan sebagai berikut.
```shell
cd bin
java Main
```
Maka, sekarang dapat dipastikan bahwa anda sudah berada dalam program yang berjalan dan siap untuk memasukkan input dan menggunakan program.

### Kasus II : Jika anda adalah user/developer yang ingin melakukan modifikasi pada program baik dalam fungsi atau prosedur yang digunakan atau mengubah kode yang terdapat pada program utama
Jika directory anda sudah berada pada folder "Algeo01-22045" tersebut maka lakukan sebagai berikut.
```shell
cd src
```
Maka, sekarang anda akan melihat adanya tiga kode dengan extension .java pada folder tersebut, yaitu `Additional.java`, `Main.java`, dan `Matrix.java`. Dengan code editor yang anda punya, anda bisa melakukan edit kode pada `Matrix.java` untuk melakukan modifikasi pada fungsi dan prosedur yang dimiliki dan edit kode pada `Main.java` jika ingin mengubah bentuk tampilan kepada user. Untuk menjalani program hasil modifikasi anda, anda bisa menulis command pada terminal sebagai berikut.
```shell
javac Main.java
java Main
```

## PERHATIAN !!!
Usahakan melakukan input kepada program dengan sebaik-baiknya sesuai dengan format yang telah ditentukan oleh program karena kesalahan input pada program dapat memicu terjadinya _bug_ pada program ataupun program terhenti seketika meskipun program telah diatur sedemikian rupa untuk dapat meng-_handle_ inputan dari user yang tidak sesuai sehingga program tidak berhenti. Namun, kegiatan tersebut sangat tidak disarankan untuk dilakukan dengan sengaja.

## Links
- Repository : https://github.com/ChaiGans/Algeo01-22045
- Issue tracker :
   - Jika menemukan kesalahan bisa melakukan email kepada elbertchailes888@gmail.com atau melakukan line dengan lineid: elbertchailes
- Github main contributor :
   - Contributor 1 (Elbert Chailes) - https://github.com/ChaiGans
   - Contributor 2 (Benardo) - https://github.com/Benardo07
   - Contributor 3 (Kharris Khisunica) - https://github.com/Kharris-Khisunica
   - Guide Assistant (Alex Sander) - https://github.com/maximatey
