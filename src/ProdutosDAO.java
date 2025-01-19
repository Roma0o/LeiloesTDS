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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        return listagem;
    }
    

    
    public void venderProduto(int parseInt) {
        try{
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement("UPDATE produtos SET status ? WHERE id = ?");
        prep.setString(1, "Vendido");
        prep.setInt(2, parseInt);
        prep.executeUpdate();
        
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar BD: " + ex.getMessage() + " ->>" + ex.getErrorCode());
        }
    }
    
    
    
        
}

