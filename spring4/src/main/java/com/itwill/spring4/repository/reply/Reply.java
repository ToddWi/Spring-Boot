package com.itwill.spring4.repository.reply;

import com.itwill.spring4.dto.reply.ReplyUpdateDto;
import com.itwill.spring4.repository.BaseTimeEntity;
import com.itwill.spring4.repository.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"post"})
@Entity
@Table(name = "REPLIES")
@SequenceGenerator(name = "REPLIES_SEQ_GEN", sequenceName = "REPLIES_SEQ1", allocationSize = 1)
public class Reply extends BaseTimeEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLIES_SEQ_GEN")
    private Long id; // Primary Key
    
    // Many = entity, one = Post (한 개의 Post에 여러 댓글이 달릴 수 있음)
    @ManyToOne(fetch = FetchType.LAZY) // EAGER(기본값): 즉시로딩, LAZY: 지연로딩
    private Post post; // 외래키, 관계를 맺고 있는 엔터티
    
    @Column(nullable = false)
    private String replyText; // 댓글 내용
    
    @Column(nullable = false)
    private String writer; // 댓글 작성자
    
    // 댓글 내용을 수정하고, 수정된 엔터티를 리턴하는 메서드:
    public Reply update(String replyText) {
        this.replyText = replyText;
        
        return this;
    }
    
}
