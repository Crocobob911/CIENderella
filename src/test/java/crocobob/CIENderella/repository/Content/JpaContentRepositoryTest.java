package crocobob.CIENderella.repository.Content;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaContentRepositoryTest {

    private JpaContentRepository repo = new JpaContentRepository();
    @Test
    void 일_이어도잘되나() {
        // 제대로 랜덤 숫자가 나오나 보자.
        System.out.println(repo.generateRandId(5));
        System.out.println(repo.generateRandId(5));
        System.out.println(repo.generateRandId(5));
        System.out.println(repo.generateRandId(5));
        System.out.println(repo.generateRandId(5));
    }
}