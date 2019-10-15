using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadLevel : MonoBehaviour {

    public GameObject StartButton;
    public Text totalTimeScore;
    public Text bestTimeScore;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        totalTimeScore.text = "" + GameMaster.secondsTotal;
        bestTimeScore.text = "" + GameMaster.bestTime;
	}

   public void StartGame(int level)
    {
        SceneManager.LoadScene(level);
    }
}
