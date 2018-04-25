package mrhi.adventure.model;

import java.io.IOException;
import java.net.Socket;

import mrhi.adventure.control.ConnectionHandler;
import mrhi.adventure.control.ConnectionManager;
import mrhi.adventure.model.vo.AccountVO;
import mrhi.adventure.model.vo.CharacterVO;

public class Client {
	
	private static Client theInstance = new Client();
	private Game game;//����Ŭ����
	private ConnectionManager connectionManager; //�����ϴ� ����Ŭ����
	private AccountManagement accountManagement = new AccountManagement();//�α��� Ŭ����
	private AccountVO myAccount;//ȸ������ Ŭ����
	
	private Client() {
		this.game = new Game();
	}
	
	public void login(AccountVO avo)
	{
		this.accountManagement.login(avo);
	}
	
	public void register(AccountVO avo)
	{
		this.accountManagement.register(avo);
	}
	
	public void createCharacter(CharacterVO charVO)
	{
		this.game.createCharacter(charVO);
	}
	
	public void requestCharacter(CharacterVO charVO)
	{
		this.game.requestCharacter(charVO);
	}
	
	public void connect()
	{
		try {
			this.connectionManager = new ConnectionManager(new Socket("192.168.0.79", 21212));
			System.out.println("���Ἲ��!");
			
			ConnectionHandler ch = new ConnectionHandler(connectionManager);
			Thread cThread = new Thread(ch);
			cThread.start();
			
		} catch (IOException e) {
			System.out.println("������ �������� �ʽ��ϴ�.");
			e.printStackTrace();
		}
	}
	
	public static Client getInstance() {
		return theInstance;
	}
	
	public AccountManagement getAccountManagement() {
		return accountManagement;
	}

	public void setAccountManagement(AccountManagement accountManagement) {
		this.accountManagement = accountManagement;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public AccountVO getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(AccountVO myAccount) {
		this.myAccount = myAccount;
	}
	
}
