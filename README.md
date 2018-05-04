#### Spotify Recsys Challenge 2018

##### Problem Introduction
As part of the class project we are aiming to create a recommendation system which we could also submit for Spotify Recsys Challenge 2018. The topic of this year’s challenge is *automatic playlist continuation*. The goal of the challenge is to develop a system for the task of automatic playlist continuation. Given a set of playlist features, the developed systems shall generate a list of recommended tracks that can be added to that playlist, thereby 'continuing' the playlist. The system should also be able to cope with playlists for which no initial seed tracks are given.  
**Input**  
A user-created playlist, represented by:
* Playlist metadata
* K seed tracks: a list of the K tracks in the playlist, where K can equal 0, 1, 5, 10, 25, or 100.  

**Output**
A list of 500 recommended candidate tracks, ordered by relevance in decreasing order.

##### Tentative Approach
We plan to implement and compare the results for this problem using three approaches:
1. K Mean clustering
2. Collaborative filtering algorithms
3. K Nearest Neighbour

##### Dataset
As part of this challenge, Spotify has released the Million Playlist Dataset. It comprises a set of 1,000,000 playlists that have been created by Spotify users, and includes playlist titles, track listings and other metadata.

##### Team
This project would be done in a team of two: Mythri Thippareddy and Harsha Kokel

#### Results

##### Heuristic 1

track Correlation =   |CommonTracks|<superscript>2</superscript> / |playlist1||playlist2|

Album Correlation =  SumOverAllExamples( (playlist1.albumweight - playlist1.averageweight)(playlist2.albumweight - playlist2.averageweight) )/Math.sqrt(SumOverAllExamples( (playlist1.albumweight - playlist1.averageweight)<superscript>2</superscript>  )SumOverAllExamples( (playlist2.albumweight - playlist2.averageweight)<superscript>2</superscript> ));

Artist Correlation = SumOverAllExamples( (playlist1.artistweight - playlist1.averageweight)(playlist2.artistweight - playlist2.averageweight) )/Math.sqrt(SumOverAllExamples( (playlist1.artistweight - playlist1.averageweight)<superscript>2</superscript>  )SumOverAllExamples( (playlist2.artistweight - playlist2.averageweight)<superscript>2</superscript> ));



Ratio is:  tracksCorrelation:albumsCorrelation:artistsCorrelation

| Track found in playlist | Artist found in playlist | Album Found in playlist | Default | precision @ 10 | precision @ 50 | precision @ 100 | precision @ 200 | precision @ 500 | precision @ 1000 |  
|-------|-------| ------- | ------ |------| -----|------|-------|-------|---- |  
| 1:0:0 | 0     | 0       | 0      | 0.01068 | 0.0882 | 0.2019 | 0.3388 | 0.5259 |0.6419 |    
| 1:0:0 | 1:1:2 | 1:2:1 | 0 | 0.0031 | 0.0215 | 0.0447 | 0.0832 | 0.1558 |  0.2466|    
| 1:0:0 | 1:1:1 | 1:1:1 | 0 | 0.0031 | 0.0158 | 0.0314 | 0.0678 | 0.1428 | 0.2262 |   
| 1:1:1 | 1:2:3 | 1:3:2 | 0 | 8.2621E-4 | 0.0040 | 0.0065 | 0.0184 | 0.04351 | 0.0872 |  
| 1:0:0 | 0:1:0 | 0:0:1| 0 | 0.00106 | 0.0053 | 0.0121 |0.02237 | 0.0526 | 0.08069 |  

##### References
* J.S. Breese, D. Heckerman, C. Kadie, "Empirical Analysis of Predictive Algorithms for Collaborative Filtering", Proc. 14th Conf. Uncertainty in Artificial Intelligence, July 1998.
