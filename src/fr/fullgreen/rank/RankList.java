package fr.fullgreen.rank;

public enum RankList {
	  ADMINISTRATOR(0, 100, "񘄵Admin", "", ">> "),
	  RESPONSABLE(1, 95, "񘬍", "", ">> "),
	  COMMUNITY(2, 94, "񗝝", "", ">> "),
	  DEV(3, 99, "�1", "", ">> "),
	  ANIMATEUR(4, 87, "񘘡", "", ">> "),
	  STAFFPLUS(5, 86, "", "", ">> "),
	  MODERATOR(6, 85, "", "", ">> "),
	  BUILDER(7, 84, "", "", ">> "),
	  ASSISTANT(8, 70, "", "", ">> "),
	  GRAPHISTE(9, 83, "", "", ">> "),
	  YOUTUBEUR(10, 70, "", "", ">> "),
	  FRIENDS(11, 70, "", "", ">> "),
	  HPARTY(12, 30, "", "", ">> "),
	  ELITE(13, 20, "", "", ">> "),
	  PLAYER(14, 10, "", "", ">> ");
	  
	  private final int power;
	  
	  private final int id;
	  
	  private final String prefix;
	  
	  private final String suffix;
	  
	  private final String chatSeparator;
	  
	  RankList(int id, int power, String prefix, String suffix, String chatSeparator) {
	    this.id = id;
	    this.power = power;
	    this.prefix = prefix;
	    this.suffix = suffix;
	    this.chatSeparator = chatSeparator;
	  }
	  
	  public final int getId() {
	    return this.id;
	  }
	  
	  public final int getPower() {
	    return this.power;
	  }
	  
	  public final String getPrefix() {
	    return this.prefix;
	  }
	  
	  public final String getSuffix() {
	    return this.suffix;
	  }
	  
	  public final String getName() {
	    return toString();
	  }
	  
	  public final String getChatSeparator() {
	    return this.chatSeparator;
	  }
	}

