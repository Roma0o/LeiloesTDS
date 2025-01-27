import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        try{
            //listagem.add(produto);
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            return status;
            
        } catch(SQLException ex){
            
        System.out.println("Erro ao conectar: " + ex.getMessage() + " ->>" + ex.getErrorCode());
        return ex.getErrorCode();
        }
    }
    
    public boolean venderProduto(int parseInt) {
        boolean status;
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            prep.setString(1, "Vendido");
            prep.setInt(2, parseInt);
            prep.executeUpdate();
            status = true;
            return status;
            
        } catch(SQLException ex){
            System.out.println("Erro ao atualizar BD: " + ex.getMessage() + " ->>" + ex.getErrorCode());
            status = false;
            return status;
        }
    }
    
    public List<ProdutosDTO> getProdutos(){
    String sql = "SELECT * FROM produtos";
    
    try{
        conn = new conectaDAO().connectDB();
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            
        List<ProdutosDTO> listaProdutos = new ArrayList<>();
            
        while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
            ProdutosDTO produto = new ProdutosDTO();
                        
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
                        
            listaProdutos.add(produto);    
        }
        return listaProdutos;
            
            
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Erro ao preencher a tabela! " + e);
        return null;
    }
    }
    
    public List<ProdutosDTO> listarProdutosVendidos(){
        boolean status;
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos WHERE status = ?");
            prep.setString(1, "Vendido");
            ResultSet rs = prep.executeQuery();
            List<ProdutosDTO> listaProdutos = new ArrayList<>();
            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO produto = new ProdutosDTO();
                        
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                        
                listaProdutos.add(produto);    
            }
            return listaProdutos;
        } catch(SQLException ex){
            System.out.println("erro");
            status = false;
            return null;
        }
    }
}

