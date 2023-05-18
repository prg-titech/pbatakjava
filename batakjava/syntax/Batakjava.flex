<YYINITIAL> {
  "ver"               { return sym(Terminals.VER); }
  "#"                 { return sym(Terminals.SHARP); }
  "«"                 { return sym(Terminals.LGUI); }
  "»"                 { return sym(Terminals.RGUI); }
}