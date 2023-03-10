import java.security.SecureRandom;

public class PasswordGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
    private static final String numerics = "1234567890";
    private static final String specialChar = "~!@#$%^&*(_+{}|:_[?]>=<";
    private static final String dictionary = uppercase+lowercase+numerics+specialChar;

    protected String generatePassword(int length){
        StringBuilder password = new StringBuilder();
        for(int i=0;i<length;i++){
            int index  = random.nextInt(dictionary.length());
            password.append(dictionary.charAt(index));
        }
        return password.toString();
    }
}
