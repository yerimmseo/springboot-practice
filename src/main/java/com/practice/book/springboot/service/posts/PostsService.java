package com.practice.book.springboot.service.posts;

import com.practice.book.springboot.domain.posts.Posts;
import com.practice.book.springboot.domain.posts.PostsRepository;
import com.practice.book.springboot.web.dto.PostsListResponseDto;
import com.practice.book.springboot.web.dto.PostsResponseDto;
import com.practice.book.springboot.web.dto.PostsSaveRequestDto;
import com.practice.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해줌
@Service
public class PostsService {
    /**
     * 스프링에서의 Bean을 주입받는 방식
     * - @Autowired --> 권장 X
     * - setter
     * - 생성자 --> 가장 권장하는 방식
     */
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        
        // update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없음. JPA의 영속성 컨텍스트 때문
        // 영속성 컨텍스트 = 엔티티를 영구 저장하는 환경
        // JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
        // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영.
        // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다.(더티 체킹)

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readOnly = true : 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)) : 넘어온 posts의 Stream을 통해 PostsListResponseDto 변환 -> List로 반환
                .collect(Collectors.toList());
    }
}
