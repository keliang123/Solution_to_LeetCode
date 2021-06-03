/*
	给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

 
示例:

输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

	解答：递归加回溯。
*/

class Solution {
    public List<String> res;
    public StringBuffer sb = new StringBuffer();
    
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if (s.length() <= 3 || s.length() > 12)  return res;
        dfs(s , 4);
        return res;
    }
    
    //深度优先遍历
    private void dfs(String s , int digit) {
        int len = s.length();
        if (len < digit) return;
        //digit==1是递归终止条件
        if (digit == 1) {
            //剪枝，长度不对，或者数值大于255 或者开头是‘0’的都不符合规范。
            if (len < 1 || len > 3 || Integer.valueOf(s) > 255) return;
            if (len > 1 && s.charAt(0) == '0') return;
            sb.append(s);
            int lastLen = sb.toString().length();
            res.add(sb.toString());
            sb.delete(lastLen - len , lastLen);
            return;
        } else {
            int maxLen = len - digit + 1;
            //把每种长度一一遍历，最长是3。 但开头是'0'时，长度只能是1.
            for (int i = 1 ; i <= maxLen && i <= 3 ; ++i) {
                String tmp = s.substring(0 , i);
                if (Integer.valueOf(tmp) > 255) continue;
                sb.append(tmp);
                sb.append(".");
                //递归后，回溯过程中要还原，所以得把添加的"tmp."删除掉。
                int tmpLen = sb.toString().length();
                dfs(s.substring(i , len) , --digit);
                digit++;
                sb.delete(tmpLen - tmp.length() - 1 , tmpLen);
                if (i == 1 && s.charAt(0) == '0') break;
            }
        }
    }
}