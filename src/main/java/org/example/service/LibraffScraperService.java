package org.example.service;

import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LibraffScraperService {

    private final BookRepository bookRepository;

    public LibraffScraperService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void scrapeBooks() {
        try {
            // 1️⃣ Saytın HTML-ni yüklə
            Document doc = Jsoup.connect("https://www.libraff.az/kitablar").get();

            // 2️⃣ Kitab elementlərini seç
            Elements books = doc.select(".product-item"); // (CSS class dəyişə bilər)

            for (Element book : books) {
                String title = book.select(".product-name a").text();
                String author = book.select(".product-author").text();
                String price = book.select(".product-price").text();

                // 3️⃣ Yeni kitab entity-si yarat
                BookEntity entity = new BookEntity();
                entity.setTitle(title);
                entity.setYear(0); // hələlik boş qala bilər
                entity.setAuthor(null); // istəsən sonradan əlavə edərik
                entity.setGenres(null);
                // istəsən price üçün ayrıca field açarsan

                // 4️⃣ DB-yə əlavə et (əgər eyni kitab yoxdursa)
                if (!bookRepository.existsByTitle(title)) {
                    bookRepository.save(entity);
                }
            }

            System.out.println("Kitablar uğurla əlavə olundu ✅");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
