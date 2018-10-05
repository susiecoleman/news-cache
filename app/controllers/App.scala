package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.NewsApi
import Actions._

class App (val controllerComponents: ControllerComponents, val newsAPI: NewsApi) extends BaseController  {

  def index: Action[AnyContent] = taskActionAsync(
    newsAPI.getArticle
      .map( a => Ok(s"Headline: ${a.title}\nSource: ${a.source.name}"))
      .onErrorRecover{case _ => BadRequest})

}