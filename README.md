# Blackjack bot
This blackjack bot uses basic blackjack strategy, web scraped from https://wizardofodds.com/, combined with card counting to simulate a game of blackjack. The user may define through the commandline how many decks are used in a simulation. Each simulation runs until there are less than 10 cards left in the deck, and by default the program will run 10k simulations. Over 50k simulations, the bot on average won roughly 51% of hands. 

This program implements card counting betting strategy by multiplying a base bet by the true count of the deck. The base bet is arbitrary, as the efficacy of the bot is measured solely in percent of hands won. 

Looking forward, this bot can be improved through the inclusion of deviation strategy, which involves the integration of card counting and play strategy. 
