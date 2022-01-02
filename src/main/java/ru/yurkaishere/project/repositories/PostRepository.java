package ru.yurkaishere.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yurkaishere.project.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
