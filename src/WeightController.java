

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WeightController
 */
@WebServlet("/WeightController")
public class WeightController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WeightController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private String getWeightResult() {

		byte[] real = null;
		byte[] message = new byte[]{ 0x5 };
		StringBuilder builder = new StringBuilder();
	    try
	    {
	        Socket s=new Socket("10.110.0.29",3002);
	        DataInputStream dis=new DataInputStream(s.getInputStream());  
	        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  

	        dout.write(message, 0, message.length);
	        dout.flush();  

	        //dout.close();  

	        byte[] data = new byte[1000];
	        int count = dis.read(data);
	        real =new byte[count+1];
	        for(int i=1;i<=count;i++){
	        real[i]=data[i];
	         
	        //int n = Integer.valueOf(real[i].intValue(), 16);
	        System.out.print("" +(char)real[i]);
	        builder.append((char)real[i]);
	        }
	        s.close();
	        System.out.println("ok");
	        }catch(Exception ex) {
	        	}
	        
	        return builder.toString();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // reading the user input
	    String color= request.getParameter("weight");    
	    PrintWriter out = response.getWriter();
	    out.println (
	     
	             this.getWeightResult() 
	    );  
	}
}
