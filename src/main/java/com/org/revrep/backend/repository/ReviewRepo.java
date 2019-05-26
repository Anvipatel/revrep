package com.org.revrep.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.org.revrep.backend.domain.dao.ReviewDAO;

@Component
public interface ReviewRepo extends PagingAndSortingRepository<ReviewDAO, String> {
	List<ReviewDAO> findByProductId(String productId);
	List<ReviewDAO> findByProductId(String productId,Pageable pageable);
}
