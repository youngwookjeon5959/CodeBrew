package scv.DevOpsunity.member.util;

import java.util.Random;

public class TempKey {
    public String getKey(int size, boolean lowerCheck) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int num;
        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < size);
        if (lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }
}