package com.ok.dvweb.repository;

import com.ok.dvweb.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
