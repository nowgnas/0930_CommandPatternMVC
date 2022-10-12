package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import member.dto.Member;
import util.DBUtil;

public class MemberDao {
    Connection con;
    private static MemberDao instance;

    private MemberDao() {
        con = DBUtil.getInstance().getConnection();
    }

    public static MemberDao getInstance() {
        if (instance == null) instance = new MemberDao();
        return instance;
    }

    public Member login(String id, String pw) {
        String sql = "select * from member where id=? and pw=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, pw);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Member(id, pw, rs.getString("name"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int memberInsert(Member member) {
        String sql = "insert into member(id, pw, name) values(?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getPw());
            stmt.setString(3, member.getName());

            return stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int checkid(String id) {
        String sql = "select * from member where id=?";
        int idcheck = 0;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() || id.equals("")) { // 이미 존재하는 경우 : 중복 !
                idcheck = 0;
            } else {
                idcheck = 1; // 존재하지 않는 경우 : 생성 가능 !
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idcheck;
    }
}

