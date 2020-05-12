package com.devgun.sample.springboot.service;

import com.devgun.sample.springboot.domain.posts.Posts;
import com.devgun.sample.springboot.domain.posts.PostsRepository;
import com.devgun.sample.springboot.web.dto.PostsResponseDto;
import com.devgun.sample.springboot.web.dto.PostsSaveRequestDto;
import com.devgun.sample.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private String exemsg = "해당 게시글이 없습니다. id=";

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(exemsg + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(exemsg + id));

        return new PostsResponseDto(entity);
    }
}
