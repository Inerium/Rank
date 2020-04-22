package fr.fullgreen.rank;

public enum RankList {
	  ADMINISTRATOR(0, 100, "�4�1Admin", "", ">> �f"),
	  RESPONSABLE(1, 95, "�6�1", "", ">> �f"),
	  COMMUNITY(2, 94, "�2�1", "", ">> �f"),
	  DEV(3, 99, "�a�1", "", ">> �f"),
	  ANIMATEUR(4, 87, "�5�1", "", ">> �f"),
	  STAFFPLUS(5, 86, "", "", ">> �f"),
	  MODERATOR(6, 85, "", "", ">> �f"),
	  BUILDER(7, 84, "", "", ">> �f"),
	  ASSISTANT(8, 70, "", "", ">> �f"),
	  GRAPHISTE(9, 83, "", "", ">> �f"),
	  YOUTUBEUR(10, 70, "", "", ">> �f"),
	  FRIENDS(11, 70, "", "", ">> �f"),
	  HPARTY(12, 30, "", "", ">> �f"),
	  ELITE(13, 20, "", "", ">> �f"),
	  PLAYER(14, 10, "", "", ">> �f");
	  
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

