package hv.hlabs.movilbox.app.view

import hv.hlabs.movilbox.app.database.entities.PostEntity

interface PostListener {

    fun onClickPost(postEntity: PostEntity)
    fun onClickFavorite(postEntity: PostEntity)

}