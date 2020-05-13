package com.devgun.sample.springboot.service;

import com.devgun.sample.springboot.domain.posts.Posts;
import com.devgun.sample.springboot.domain.posts.PostsRepository;
import com.devgun.sample.springboot.web.dto.PostsListResponseDto;
import com.devgun.sample.springboot.web.dto.PostsResponseDto;
import com.devgun.sample.springboot.web.dto.PostsSaveRequestDto;
import com.devgun.sample.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private String exemsg = "해당 게시글이 없습니다. id=";

    /**
    * 저장로직
    */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 업데이트
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(exemsg + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    /**
     * 해당 아이디찾기
     */
    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(exemsg + id));

        return new PostsResponseDto(entity);
    }

    /**
     * 리스트 조회
     */
    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto:: new)
                .collect(Collectors.toList());
    }

    /**
     * 삭제
     */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(exemsg + id));
        postsRepository.delete(posts); //1. JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용, 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다. 존재하는 Posts 인지 확인을 위해서 엔티티 조회 후 그대로 삭제
    }
}
