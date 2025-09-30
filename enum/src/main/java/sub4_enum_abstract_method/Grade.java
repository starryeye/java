package sub4_enum_abstract_method;

public enum Grade {

    SILVER("실버") {
        @Override
        public double calculateDiscount(double price) {
            return price * 0.05; // 5% 할인
        }

        @Override
        public String getDescription() {
            return "Silver Grade: 5% discount on purchases";
        }
    },
    GOLD("골드") {
        @Override
        public double calculateDiscount(double price) {
            return price * 0.10; // 10% 할인
        }

        @Override
        public String getDescription() {
            return "Gold Grade: 10% discount on purchases";
        }
    },
    PLATINUM("플레티넘") {
        @Override
        public double calculateDiscount(double price) {
            return price * 0.15; // 15% 할인
        }

        @Override
        public String getDescription() {
            return "Platinum Grade: 15% discount on purchases";
        }
    },
    DIAMOND("다이아") {
        @Override
        public double calculateDiscount(double price) {
            return price * 0.20; // 20% 할인
        }

        @Override
        public String getDescription() {
            return "Diamond Grade: 20% discount on purchases";
        }
    };

    // 추상 메서드
    public abstract double calculateDiscount(double price);
    public abstract String getDescription();



    private final String koreanName;

    private Grade(String koreanName) {
        this.koreanName = koreanName;
    }

    // 공통 메서드
    public String getGradeKoreanName() {
        return this.koreanName;
    }
}
