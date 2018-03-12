# Spotify Recsys Challenge 2018

### Problem Introduction

As part of the class project we are aiming to create a recommendation system which we could also submit for Spotify Recsys Challenge 2018. The topic of this year’s challenge is *automatic playlist continuation*. The goal of the challenge is to develop a system for the task of automatic playlist continuation. Given a set of playlist features, the developed systems shall generate a list of recommended tracks that can be added to that playlist, thereby 'continuing' the playlist. The system should also be able to cope with playlists for which no initial seed tracks are given.

##### Input

A user-created playlist, represented by:  
* Playlist metadata (see the dataset section)  
* K seed tracks: a list of the K tracks in the playlist, where K can equal 0, 1, 5, 10, 25, or 100.

##### Output

* A list of 500 recommended candidate tracks, ordered by relevance in decreasing order.

### Dataset

As part of this challenge, Spotify has released the Million Playlist Dataset. It comprises a set of 1,000,000 playlists that have been created by Spotify users, and includes playlist titles, track listings and other metadata.

##### Format

The Million Playlist Dataset consists of 1,000 slice files. These files have the naming convention of:

mpd.slice.STARTING_PLAYLIST\_ID\_-\_ENDING\_PLAYLIST\_ID.json

For example, the first 1,000 playlists in the MPD are in a file called mpd.slice.0-999.json and the last 1,000 playlists are in a file called mpd.slice.999000-999999.json.

Each slice file is a JSON dictionary with two fields: Info and Playlists.

###### Info Field

The info field is a dictionary that contains general information about the particular slice:

* slice - the range of slices that in in this particular file - such as 0-999
* version - the current version of the MPD (which should be v1)
* generated_on - a timestamp indicating when the slice was generated.
playlists field

This is an array that typically contains 1,000 playlists. Each playlist is a dictionary that contains the following fields:

* pid - integer - playlist id - the MPD ID of this playlist. This is an integer between 0 and 999,999.  
* name - string - the name of the playlist
* description - optional string - if present, the description given to the playlist. Note that user-provided playlist descrptions are a relatively new feature of Spotify, so most playlists do not have descriptions.  
* modified_at - seconds - timestamp (in seconds since the epoch) when this playlist was last updated. Times are rounded to midnight GMT of the date when the playlist was last updated.  
* num_artists - the total number of unique artists for the tracks in the playlist.  
* num_albums - the number of unique albums for the tracks in the playlist
* num_tracks - the number of tracks in the playlist
* num_followers - the number of followers this playlist had at the time the MPD was created. (Note that the follower count does not including the playlist creator)
* num_edits - the number of separate editing sessions. Tracks added in a two hour window are considered to be added in a single editing session.
duration_ms - the total duration of all the tracks in the playlist (in milliseconds)
* collaborative - boolean - if true, the playlist is a collaborative playlist. Multiple users may contribute tracks to a collaborative playlist.
* tracks - an array of information about each track in the playlist. Each element in the array is a dictionary with the following fields:
    * track_name - the name of the track
    * track_uri - the Spotify URI of the track
    * album_name - the name of the track's album
    * album_uri - the Spotify URI of the album
    * artist_name - the name of the track's primary artist
    * artist_uri - the Spotify URI of track's primary artist
    * duration_ms - the duration of the track in milliseconds
    * pos - the position of the track in the playlist (zero-based)
