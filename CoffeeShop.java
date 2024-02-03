
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
public class CoffeeShop extends JFrame implements ActionListener{
	Container cp;
	JLabel logo;
	JPanel content,personal,order,actions,details;
	JLabel nameLabel,phoneLabel,coffeeLabel,quLabel;
	JTextField name,phone,quantity;
	JComboBox coffees;
	JButton submit,exit,add,remove;
	JTextArea detailsTxt;
	JScrollPane scroll;
	ArrayList<Coffee> orderList;
	public CoffeeShop() {
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		logo = new JLabel(new ImageIcon("Cof.png"));
		cp.add(logo,BorderLayout.NORTH);
		content = new JPanel(new GridLayout(3,1));
		personal = new JPanel(new GridLayout(2,2,0,10));
		nameLabel = new JLabel("Full name:");
		name = new JTextField(15);
		phoneLabel = new JLabel("Phone:");
		phone = new JTextField(15);
		personal.add(nameLabel);
		personal.add(name);
		personal.add(phoneLabel);
		personal.add(phone);
		personal.setBorder(BorderFactory.createTitledBorder("Personal Info: "));
		content.add(personal);
		order = new JPanel(new GridLayout(3,2,5,10));
		order.setBorder(BorderFactory.createTitledBorder("Order Info: "));
		coffeeLabel = new JLabel("Coffee:");
		order.add(coffeeLabel);
		Coffee names[] = {new Coffee("Turkey",3),new Coffee("Espresso",4),new Coffee("Latte",4),new Coffee("Mocca",5),new Coffee("Flat White",8)};
		coffees = new JComboBox(names);
		order.add(coffees);
		quLabel = new JLabel("Quantity: ");
		order.add(quLabel);
		quantity = new JTextField();
		order.add(quantity);
		add = new JButton("Add");
		add.addActionListener(this);
		remove = new JButton("Remove");
		remove.addActionListener(this);
		order.add(add);
		order.add(remove);
		details = new JPanel(new BorderLayout());
		details.setBorder(BorderFactory.createTitledBorder("Order details:"));
		detailsTxt = new JTextArea();
		detailsTxt.setEditable(false);
		scroll = new JScrollPane(detailsTxt);
		details.add(scroll);
		content.add(order);
		content.add(details);
		submit = new JButton("Submit");
		submit.setBackground(new Color(111,78,55));
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		exit = new JButton("Exit");
		exit.setBackground(Color.red);
		exit.setForeground(Color.white);
		exit.addActionListener(this);
		orderList = new ArrayList<>();
		actions = new JPanel(new FlowLayout());
		actions.add(submit);
		actions.add(exit);
		cp.add(actions,BorderLayout.SOUTH);
		cp.add(content,BorderLayout.CENTER);
		setTitle("Coffee Shop");
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new CoffeeShop();
	}
	public void actionPerformed(ActionEvent e) {
		//JButton source = (JButton) e.getSource();
		Coffee selected;		
		if(e.getSource() == add) {
			selected = (Coffee) coffees.getSelectedItem();
			orderList.add(new Coffee(selected.getName(),selected.getPrice(),Integer.parseInt(quantity.getText())));
		}else if(e.getSource() == remove) {
			selected = (Coffee) coffees.getSelectedItem();
			for(int i = 0 ; i < orderList.size() ; i++) {
				if(orderList.get(i).getName() == selected.getName()) {
					orderList.remove(i);
				}
			}
		}else if(e.getSource() == submit) {
			String result = "Name: "+name.getText()+"\n"
							+"Phone: "+phone.getText()+"\n";
			float total = 0;
			for(int i = 0 ; i < orderList.size() ; i++) {
				selected = orderList.get(i);
				result += "Coffee: "+selected.getName()+" Quantity: "+selected.getQuantity()+"\n";
				total += selected.getPrice()*selected.getQuantity();
			}
			result +="------------------------------\n";
			result +="Total price: "+total+" SAR";
			JOptionPane.showMessageDialog(this,result);

		}else if(e.getSource() == exit) {
			System.exit(0);
		}
		detailsTxt.setText("");
		for(int i = 0 ; i < orderList.size() ; i++) {
			selected = orderList.get(i);
			detailsTxt.append("Coffee: "+selected.getName()+" Quantity: "+selected.getQuantity()+"\n");
		}
		
	}

}