public class Solution {
    public boolean isNumber(String s) {
        boolean result = false;
        if(s.matches("[ ]*([-+]?([0-9]+)|([-+]?[0-9]+\\.[0-9]*)|([-+]?\\.[0-9]+))([eE][-+]?[0-9]+)?[ ]*")) result=true;
        return result;
    }
}
