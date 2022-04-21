import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class JAVAexamples {

	/**
	 * @param args
	 */
	static Connection minConnection;
	static Statement stmt;
	static BufferedReader inLine;


	public static void SamletSalgiKrForProdukt() {
	try {
		// Indlæser søgestreng
		System.out.println("Indtast produkt beskrivelse ");
		String produktBeskrivelse = inLine.readLine();
		System.out.println("Indtast dato der ønskes at finde samlet salg på. eks (2022.07.01)");
		String Dato = inLine.readLine();
		// Laver sql-sætning og får den udført

		String sql = "select p.beskrivelse,COALESCE(ol.aftaltpris, SUM((p.pris * ol.antal) - ((p.pris * ol.antal) / 100) * p.procentrabat)) as 'Pris' " +
				"from salg s join ordrelinje ol on s.salgsnr = ol.salgsnr " +
				"join pris p on ol.prisid = p.prisid " +
				"where s.salgsdato = '" + Dato + "'and p.beskrivelse like '" + produktBeskrivelse + "' " +
				"group by s.salgsnr,beskrivelse,ol.aftaltpris";

		System.out.println("SQL-streng er "+ sql);
		ResultSet res=stmt.executeQuery(sql);
		//gennemløber svaret

		double samletPris = 0;
		while (res.next()) {
			samletPris += Double.parseDouble(res.getString(2));
		}
		System.out.println("Produkt: " + produktBeskrivelse + " Dato: " + Dato + " Indtjening: " + samletPris + "Kr");
		// pæn lukning
 		if (!minConnection.isClosed()) minConnection.close();
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}


	public static void opretProdukt() {
		try {
			// indlæsning
			System.out.println("Vi vil nu oprette et nyt produkt");
			System.out.println("Indtast beskrivelsen på produktet");
			String produktBeskrivelse = inLine.readLine();
			System.out.println("Indtast antallet af det pågældende produkt på lager");
			String produktAntalPåLager = inLine.readLine();
			System.out.println("Indtast minimum antallet af det pågældende produkt på lager");
			String produktMinAntalPåLager = inLine.readLine();
			System.out.println("Indtast produktType på det pågældende produkt(Produktgruppe skal være oprettet)");
			String produktType = inLine.readLine();
		
			// sender insert'en til db-serveren
			String sql = "insert into produkt values ('" + produktBeskrivelse + "'," + produktAntalPåLager + "," + produktMinAntalPåLager + ",'" + produktType + "')";
			System.out.println("SQL-streng er "+ sql);
			stmt.execute(sql);
			// pænt svar til brugeren
			System.out.println("Produktet er nu oprettet");
			if (!minConnection.isClosed()) minConnection.close();
		}
		catch (SQLException e) {
			        switch (e.getErrorCode())
			        // fejl-kode 547 svarer til en foreign key fejl
			        { case 547 : {if (e.getMessage().contains("produkt_foreign"))
										System.out.println("produktgruppe er ikke oprettet");
			        			  else
			        				    System.out.println("ukendt fremmednøglefejl");
								  break;
			        			}
			        // fejl-kode 2627 svarer til primary key fejl
			          case 2627: {System.out.println("den pågældende produkt er allerede oprettet");
			          	          break;
			         			 }
			          default: System.out.println("fejlSQL:  "+e.getMessage());
				};
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	};


	public static void opretOrdrelinje() {
		try {
			// indlæsning
			System.out.println("Vi vil nu oprette en ny ordrelinje");
			System.out.println("Indtast beskrivelsen på produktet");
			String produktBeskrivelse = inLine.readLine();

			String sql = "select prisid " +
					"from pris p join produkt pt on p.beskrivelse = pt.beskrivelse " +
					"where pt.beskrivelse like '" + produktBeskrivelse + "'";
			ResultSet res=stmt.executeQuery(sql);
			int prisId = 0;
			while (res.next()) {
				prisId = res.getInt(1);
			}

			System.out.println("Indtast antallet af det pågældende der ønskes");
			String antalProdukt = inLine.readLine();
			System.out.println("Indtast aftalt pris, hvis dette findes");
			String AftaltPris = inLine.readLine();
			System.out.println("Indtast salgsNr, for det salg ordrelinjen skal tilhøre)");
			String salgsNr = inLine.readLine();

			// sender insert'en til db-serveren
			sql = "select antalpålager,minimumantal from produkt p where p.beskrivelse like '" + produktBeskrivelse + "'";
			res=stmt.executeQuery(sql);
			int antalPåLager = 0;
			int minimumAntal = 0;
			while (res.next()) {
				antalPåLager = res.getInt(1);
				minimumAntal = res.getInt(2);
			}

			sql = "insert into ordrelinje values ('" + antalProdukt + "'," + AftaltPris + "," + salgsNr + ",'" + prisId + "')";
			System.out.println("SQL-streng er "+ sql);
			stmt.execute(sql);
			// pænt svar til brugeren
			System.out.println("Ordrelinje er nu oprettet");
			if (antalPåLager - Integer.parseInt(antalProdukt) < minimumAntal){
				System.out.println("Antal på lager er nået et minimum (Bestil straks Dr buchwald!)");
			}
			if (!minConnection.isClosed()) minConnection.close();

		}
		catch (SQLException e) {
			switch (e.getErrorCode())
			// fejl-kode 547 svarer til en foreign key fejl
			{ case 547 : {if (e.getMessage().contains("salgsnr_foreign"))
				System.out.println("Salg er ikke oprettet");
			else
			if (e.getMessage().contains("antalundernul"))
				System.out.println("Ønsket mængde er ikke på lager");
			else
				System.out.println("ukendt fremmednøglefejl");
				break;
			}
				default: System.out.println("fejlSQL:  "+e.getMessage());
			};
		}

		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	};


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			inLine = new BufferedReader(new InputStreamReader(System.in));
			//generel opsætning
			//via native driver
			String server="localhost\\SQLEXPRESS"; //virker måske hos dig
			                                       //virker det ikke - prøv kun med localhost
			String dbnavn="AarhusBryghusDB";            //virker måske hos dig
			String login="sa";                     //skal ikke ændres
			String password="12345";            //skal ændres
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager.getConnection("jdbc:sqlserver://"+server+";databaseName="+dbnavn+
					";user=" + login + ";password=" + password + ";");
			//minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=eksempeldb;user=sa;password=torben07;");
			stmt = minConnection.createStatement();
			//Indlæsning og kald af den rigtige metode
			System.out.println("Indtast  ");
			System.out.println("Skriv (opret produkt) for oprette et produkt");
			System.out.println("skriv (samlet salg i kr) for at finde samlet salg i kr for et produkt");
			System.out.println("Skriv (opret ordrelinje) for at oprette ordrelinje");
			String in=inLine.readLine();
			switch (in)
			{case "samlet salg i kr" : {
				 SamletSalgiKrForProdukt();break;}
			 case "opret produkt"  : {opretProdukt();break;}
			 case "opret ordrelinje"  : {opretOrdrelinje();break;}
			default : System.out.println("ukendt indtastning"); 
			} 
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}


}

