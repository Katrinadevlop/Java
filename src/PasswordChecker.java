public class PasswordChecker {

    private Integer minLength = null;
    private Integer maxRepeats = null;

    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Не подходит!");
        }
        this.minLength = minLength;
    }

    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Не подходит!");
        }
        this.maxRepeats = maxRepeats;
    }

    public boolean verify(String password) {
        if (minLength == null && maxRepeats == null){
            throw new IllegalStateException("Настройки не установлены.");
        }

        if (password.length() < minLength){
            return false;
        }

        int countRepeat = 0;
        for (int i = 0; i < password.length()-1; i++){
            if (password.charAt(i) == password.charAt(i+1)){
                countRepeat++;
                if(countRepeat > maxRepeats){
                    return false;
                }
            }
            else {
                countRepeat = 0;
            }
        }
        return true;
    }
}
