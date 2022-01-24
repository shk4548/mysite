package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	public List<BoardVo> findall() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.*,b.name from board as a join user as b on a.user_no"
					+ " = b.no order by g_no desc, o_no asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				int groupNo = rs.getInt(5);
				int orderNo = rs.getInt(6);
				int depth = rs.getInt(7);
				String regDate = rs.getString(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "Insert into board values ( null , ? , ? , 0, "
					+ "(select ifnull(max(g_no)+1,1) from board b) , 1 , 1 ,now(), ?);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean replyWrite(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			String sql = "Insert into board values(null ,?,?,0, ?  "
					+ ", ? , ?, now() , ?);";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getGroupNo());
			pstmt.setInt(4, vo.getOrderNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> search(String keyword) {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.*,b.name from board as a join user as b on a.user_no"
					+ " = b.no where a.title like '%" + keyword + "%' or" + " b.name like '%" + keyword
					+ "%' order by g_no desc, o_no asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			list = new ArrayList<BoardVo>();

			while (rs.next()) {

				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContents(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setGroupNo(rs.getInt(5));
				vo.setOrderNo(rs.getInt(6));
				vo.setDepth(rs.getInt(7));
				vo.setRegDate(rs.getString(8));
				vo.setUserNo(rs.getLong(9));
				vo.setUserName(rs.getString(10));

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean delete(Long no,Long userNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setLong(2, userNo);
			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo findByNo(Long no) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select title, contents,  hit, g_no, o_no, depth, a.reg_date ,user_no, b.name from board as a join user as \r\n"
					+ "b on a.user_no = b.no where a.no = ? order by g_no desc, o_no asc ;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				int groupNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				String regDate = rs.getString(7);
				Long userNo = rs.getLong(8);
				String userName = rs.getString(9);

				result = new BoardVo();

				result.setNo(no);
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setGroupNo(groupNo);
				result.setOrderNo(orderNo);
				result.setDepth(depth);
				result.setRegDate(regDate);
				result.setUserNo(userNo);
				result.setUserName(userName);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean updateHit(int cnt, long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set hit = ? where no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setLong(2, no);

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean updateReply(int orderno , int groupno) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "update board set o_no = (o_no + 1) where"

					+ " o_no > ? and g_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderno);
			pstmt.setInt(2, groupno);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "";
			if (vo.getTitle() == "") {
				sql = "update board set contents = ? where no = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getContents());
				pstmt.setLong(2, vo.getNo());

				int count = pstmt.executeUpdate();
				result = count == 1;

			} else if (vo.getContents() == "") {
				sql = "update board set title = ? where no = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setLong(2, vo.getNo());

				int count = pstmt.executeUpdate();
				result = count == 1;

			} else {

				sql = "update board set title = ? , contents = ? where no = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getNo());

				int count = pstmt.executeUpdate();
				result = count == 1;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}
