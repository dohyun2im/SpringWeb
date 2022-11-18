package com.EzenWeb.domain.Dao;

import com.EzenWeb.domain.Dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb",
                                              "root",
                                              "1234");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean setboard(BoardDto dto) {
        String sql = "insert into board (btitle,bcontent) values (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getBtitle());
            ps.setString(2, dto.getBcontent());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<BoardDto> getboards() {
        ArrayList<BoardDto> list = new ArrayList<>();
        String sql = "select * from board";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BoardDto dto =BoardDto.builder()
                                      .btitle(rs.getString(2))
                                      .bcontent(rs.getString(3))
                                      .build();
                list.add(dto);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
