package molly.core

import cats.effect.kernel.Async
import com.mongodb.client.MongoCollection
import molly.core.query.SyncWatchQuery
import org.bson.BsonDocument

/** Molly's counterpart to
  * [[https://mongodb.github.io/mongo-java-driver/5.1/apidocs/mongodb-driver-sync/com/mongodb/client/MongoCollection.html MongoCollection]].
  */
final case class MollySyncCollection[F[_], A] private[core] (private[core] val delegate: MongoCollection[BsonDocument])(
    using
    Async[F],
    MollyCodec[F, A]
):

  /** [[https://mongodb.github.io/mongo-java-driver/5.1/apidocs/mongodb-driver-sync/com/mongodb/client/MongoCollection.html#watch()]]
    */
  def watch(): SyncWatchQuery[F, A] = SyncWatchQuery(delegate.watch(classOf[BsonDocument]))
