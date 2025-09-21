package reflection.sub5_practice;

public class AvoidNull1 {

    /**
     * 요구사항.
     * Article, Comment 객체에 어떤 이유에서 필드 값이 null 로 채워져있고..
     * 해당 객체의 데이터를 어딘가 저장해야하는데.. 이를 빈값으로라도 채워서 null 인 필드를 없애야 한다고치자..
     *
     *
     * reflection 을 사용하지 않고 "정적" 방식으로 개발한다면...
     * 아래 코드와 같이 한땀한땀.. 다뤄줘야한다..
     */

    public static void main(String[] args) {

        // given
        Article article = new Article(1L, null, null);
        Comment comment = new Comment(1L, null);
        System.out.println("======= before =======");
        System.out.println("article = " + article);
        System.out.println("comment = " + comment);


        // null 회피 코드
        if (article.getId() == null) {
            article.setId(0L);
        }
        if (article.getTitle() == null) {
            article.setTitle("");
        }
        if (article.getContent() == null) {
            article.setContent("");
        }
        if (comment.getId() == null) {
            comment.setId(0L);
        }
        if (comment.getComment() == null) {
            comment.setComment("");
        }


        System.out.println("======= after =======");
        System.out.println("article = " + article);
        System.out.println("comment = " + comment);
    }
}
