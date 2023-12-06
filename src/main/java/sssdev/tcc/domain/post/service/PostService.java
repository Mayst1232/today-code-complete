package sssdev.tcc.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sssdev.tcc.domain.post.dto.response.PostDetailResponse;
import sssdev.tcc.domain.post.repository.PostRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 모든 게시글을 가져옴. query 값이 존재하면 query 포함 게시글을 가져옴
     */
    public Page<PostDetailResponse> getPosts(Pageable pageable, String query) {

        if (query.isBlank()) {
            return postRepository.findAll(pageable)
                .map(PostDetailResponse::of);
        }

        return postRepository.findAllByContentContaining(query, pageable)
            .map(PostDetailResponse::of);
    }
}
